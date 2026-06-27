package com.quickbite.kafka.producer;

import com.quickbite.kafka.event.OrderPlacedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaProducerService {
    private final KafkaTemplate<String, OrderPlacedEvent> kafkaTemplate;
    private static final String TOPIC="order-created";
    public void sendMessage(OrderPlacedEvent event) {
        kafkaTemplate.send(TOPIC, event);
        System.out.println("Event Published : " + event);
    }
}
