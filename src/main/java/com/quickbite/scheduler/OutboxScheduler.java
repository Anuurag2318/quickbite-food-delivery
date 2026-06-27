package com.quickbite.scheduler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.quickbite.entity.OutboxEvent;
import com.quickbite.entity.OutboxStatus;
import com.quickbite.kafka.event.OrderPlacedEvent;
import com.quickbite.kafka.producer.KafkaProducerService;
import com.quickbite.repository.OutboxEventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OutboxScheduler {
    private final OutboxEventRepository outboxEventRepository;
    private final KafkaProducerService kafkaProducerService;
    private final ObjectMapper objectMapper;
    @Scheduled(fixedDelay = 5000)
    public void processEvents() throws JsonProcessingException {
        List<OutboxEvent> events=outboxEventRepository.findTop100ByStatusOrderByCreatedAtAsc(OutboxStatus.PENDING);
        for(OutboxEvent outbox: events){
            try{
                OrderPlacedEvent event=objectMapper.readValue(outbox.getPayload(),OrderPlacedEvent.class);
                kafkaProducerService.sendMessage(event);
                outbox.setStatus(OutboxStatus.PROCESSED);
                outbox.setProcessedAt(LocalDateTime.now());
                outboxEventRepository.save(outbox);
            }catch (Exception e){
                System.out.println("Some issue with the kafka");
            }

        }
    }
}
