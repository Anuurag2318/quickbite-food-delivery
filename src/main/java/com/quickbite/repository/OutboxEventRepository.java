package com.quickbite.repository;

import com.quickbite.entity.OutboxEvent;
import com.quickbite.entity.OutboxStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OutboxEventRepository extends JpaRepository<OutboxEvent,Long> {
    List<OutboxEvent > findTop100ByStatusOrderByCreatedAtAsc(OutboxStatus status);
}
