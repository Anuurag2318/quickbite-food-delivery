package com.quickbite.entity;

import jakarta.persistence.*;
import lombok.*;

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

}
