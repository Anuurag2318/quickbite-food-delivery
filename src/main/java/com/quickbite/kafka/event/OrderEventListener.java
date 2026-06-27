//package com.quickbite.kafka.event;
//
//import com.quickbite.kafka.producer.KafkaProducerService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.event.TransactionPhase;
//import org.springframework.transaction.event.TransactionalEventListener;
//
//@Component
//@RequiredArgsConstructor
//public class OrderEventListener {
//    private final KafkaProducerService kafkaProducerService;
//
//    @TransactionalEventListener(phase= TransactionPhase.AFTER_COMMIT)
//    public void handleOrderCreated(OrderCreatedSpringEvent event){
//        kafkaProducerService.sendMessage(event.getOrderPlacedEvent());
//    }
//}
