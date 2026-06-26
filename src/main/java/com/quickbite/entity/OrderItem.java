package com.quickbite.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="order_items")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer quantity;
    private Double price;
    @ManyToOne
    @JoinColumn(name="food_item_id")
    private FoodItem foodItem;
    @ManyToOne
    @JoinColumn(name="order_id")
    private Order order;
}
