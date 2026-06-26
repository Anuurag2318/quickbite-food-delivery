package com.quickbite.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name="food_items")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FoodItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Double price;

    @ManyToOne
    @JoinColumn(name="restaurant_id")
    private Restaurant restaurant;

    @OneToMany(mappedBy = "foodItem")
    private List<OrderItem> orderItems;
}
