package com.quickbite.service;

import com.quickbite.dto.OrderItemRequest;
import com.quickbite.dto.OrderRequest;
import com.quickbite.dto.OrderResponse;
import com.quickbite.entity.*;
import com.quickbite.exception.ResourceNotFoundException;
import com.quickbite.repository.FoodItemRepository;
import com.quickbite.repository.OrderItemRepository;
import com.quickbite.repository.OrderRepository;
import com.quickbite.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
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
    @Override
    public OrderResponse placeOrder(OrderRequest request) {
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
        return OrderResponse.builder()
                .id(order.getId())
                .totalAmount(order.getTotalAmount())
                .status(OrderStatus.PLACED).build();
    }
}
