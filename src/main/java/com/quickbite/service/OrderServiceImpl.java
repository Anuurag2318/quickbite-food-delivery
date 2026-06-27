package com.quickbite.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.quickbite.dto.*;
import com.quickbite.entity.*;
import com.quickbite.exception.InvalidOrderStatusException;
import com.quickbite.exception.ResourceNotFoundException;
import com.quickbite.kafka.event.OrderPlacedEvent;
import com.quickbite.kafka.producer.KafkaProducerService;
import com.quickbite.repository.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderServiceImpl implements OrderService{
    private final OrderRepository orderRepository;
    private final FoodItemRepository foodItemRepository;
    private final OrderItemRepository orderItemRepository;
    private final UserRepository userRepository;
    private final ApplicationEventPublisher applicationEventPublisher;
    private final OutboxEventRepository outboxEventRepository;
    private final ObjectMapper objectMapper;
    @Override
    public OrderResponse placeOrder(OrderRequest request) throws JsonProcessingException {
        User user=userRepository.findById(request.getUserId())
                .orElseThrow(()->new ResourceNotFoundException("User not found"));
        if(request.getItems().isEmpty()){
            throw new ResourceNotFoundException("Items are not available");
        }
        Order order=Order.builder()
                .user(user)
                .status(OrderStatus.PLACED)
                .orderDate(LocalDateTime.now())
                .totalAmount(0.0)
                .build();
        order=orderRepository.save(order);
        double totalAmount=0.0;
        List<OrderItemRequest> orderItems=request.getItems();
        for(OrderItemRequest itemRequest:orderItems){
            FoodItem foodItem=foodItemRepository.findById(itemRequest.getFoodItemId())
                    .orElseThrow(()->new ResourceNotFoundException("Food Item not found"));
            double subTotal=foodItem.getPrice()*itemRequest.getQuantity();
            totalAmount+=subTotal;
            OrderItem orderItem=OrderItem.builder()
                    .order(order)
                    .foodItem(foodItem)
                    .quantity(itemRequest.getQuantity())
                    .price(foodItem.getPrice())
                    .build();
            orderItemRepository.save(orderItem);
        }
        order.setTotalAmount(totalAmount);
        orderRepository.save(order);
        OrderPlacedEvent event=OrderPlacedEvent.builder()
                .orderId(order.getId())
                .userId(user.getId())
                .totalAmount(order.getTotalAmount())
                .status(order.getStatus())
                .build();
        String payload=objectMapper.writeValueAsString(event);
        OutboxEvent outbox=OutboxEvent.builder()
                .eventType("ORDER_PLACED")
                .payload(payload)
                .status(OutboxStatus.PENDING)
                .createdAt(LocalDateTime.now())
                .build();
        outboxEventRepository.save(outbox);
//        applicationEventPublisher.publishEvent(new OrderCreatedSpringEvent(event));
        return OrderResponse.builder()
                .id(order.getId())
                .totalAmount(order.getTotalAmount())
                .status(OrderStatus.PLACED).build();
    }

    @Override
    public Page<OrderResponse> getOrdersByUserId(Long userId,int page,int limit) {
        Pageable pageable= PageRequest.of(page,limit, Sort.by("orderDate").descending());
        Page<Order> orders=orderRepository.findByUser_Id(userId,pageable);
        return orders.map(order->OrderResponse.builder()
                .id(order.getId())
                .totalAmount(order.getTotalAmount())
                .status(order.getStatus()).build());
    }

    @Override
    public OrderResponse updateOrderStatus(Long orderId, UpdateOrderStatusRequest request) {
        Order order=orderRepository.findById(orderId).orElseThrow(()->new ResourceNotFoundException("Order not found"));
        OrderStatus currentStatus=order.getStatus();
        OrderStatus newStatus=request.getStatus();
        if(!isValidStatusTransition(currentStatus,newStatus)){
            throw new InvalidOrderStatusException("Invalid order status cannot be updated");
        }
        order.setStatus(newStatus);
        orderRepository.save(order);
        return OrderResponse.builder()
                .id(order.getId())
                .totalAmount(order.getTotalAmount())
                .status(order.getStatus()).build();
    }

    @Override
    public OrderResponse cancelOrder(Long orderId) {
        Order order=orderRepository.findById(orderId).orElseThrow(()->new ResourceNotFoundException("Order not found"));
        if(order.getStatus()==OrderStatus.DELIVERED){
            throw new InvalidOrderStatusException("Delivered order cannot be cancelled");
        }
        if(order.getStatus()==OrderStatus.CANCELLED){
            throw new InvalidOrderStatusException("Order is already cancelled");
        }
        order.setStatus(OrderStatus.CANCELLED);
        orderRepository.save(order);
        return OrderResponse.builder()
                .id(order.getId())
                .status(order.getStatus())
                .totalAmount(order.getTotalAmount()).build();
    }

    @Override
    public OrderTrackingResponse trackOrder(Long orderId) {
        Order order=orderRepository.findById(orderId).orElseThrow(()->new ResourceNotFoundException("Order not found"));
        String estimatedDeliveryTime=getStatusForEach(order.getStatus());
        return OrderTrackingResponse.builder()
                .orderId(order.getId())
                .status(order.getStatus())
                .estimatedDeliveryTime(estimatedDeliveryTime).build();
    }
    private boolean isValidStatusTransition(OrderStatus currentStatus,OrderStatus newStatus){
        switch(currentStatus){
            case PLACED:
                return newStatus==OrderStatus.CONFIRMED ||
                        newStatus==OrderStatus.CANCELLED;
            case CONFIRMED:
                return  newStatus == OrderStatus.PREPARING ||
                        newStatus == OrderStatus.CANCELLED;

            case PREPARING:
                return newStatus == OrderStatus.OUT_FOR_DELIVERY ||
                        newStatus == OrderStatus.CANCELLED;

            case OUT_FOR_DELIVERY:
                return newStatus == OrderStatus.DELIVERED;
            default:
                return false;
        }
    }
    private String getStatusForEach(OrderStatus status){
        switch(status) {
            case PLACED:
                return "Restaurant has received your order.";
            case CONFIRMED:
                return "Restaurant confirmed your order.";
            case PREPARING:
                return "Your food is being prepared.";
            case OUT_FOR_DELIVERY:
                return "30 minutes";
            case DELIVERED:
                return "Order Delivered.";
            case CANCELLED:
                return "Order Cancelled";
            default:
                throw new InvalidOrderStatusException("Invalid order status");
        }
    }
}
