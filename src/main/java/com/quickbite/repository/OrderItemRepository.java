package com.quickbite.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<com.quickbite.entity.OrderItem,Long> {
}
