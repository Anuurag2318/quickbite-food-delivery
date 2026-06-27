package com.quickbite.kafka.consumer;

import com.quickbite.kafka.event.OrderPlacedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaConsumerService {
    @KafkaListener(topics="order-created",groupId = "quickbite-group")
    public void consume(OrderPlacedEvent event){
        try{
            System.out.println("==================================");
            System.out.println("Notification Service");
            System.out.println("Order ID : " + event.getOrderId());
            System.out.println("User ID  : " + event.getUserId());
            System.out.println("Sending Order Confirmation...");
            System.out.println("==================================");
        }
        catch(Exception e){
            e.printStackTrace();
        }

    }
}
