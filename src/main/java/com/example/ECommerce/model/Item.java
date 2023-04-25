package com.example.ECommerce.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.cfg.AccessType;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="item")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    int requiredQuantity;

    @ManyToOne
    @JoinColumn
    Cart cart;

    @ManyToOne
    @JoinColumn
    Ordered order;

    @ManyToOne
    @JoinColumn
    Product product;

}
