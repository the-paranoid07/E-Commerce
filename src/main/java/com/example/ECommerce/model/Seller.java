package com.example.ECommerce.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="seller")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Seller {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String name;
    int age;
    String mobNo;
    @Column(unique = true)
    String emailId;

    @OneToMany(mappedBy = "seller",cascade = CascadeType.ALL)
    List<Product>products=new ArrayList<>();

}
