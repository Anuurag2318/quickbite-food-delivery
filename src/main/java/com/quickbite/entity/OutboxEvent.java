package com.quickbite.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name="outbox_events")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OutboxEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String eventType;
    @Column(columnDefinition = "TEXT")
    private String payload;
    @Enumerated(EnumType.STRING)
    private OutboxStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime processedAt;

}
