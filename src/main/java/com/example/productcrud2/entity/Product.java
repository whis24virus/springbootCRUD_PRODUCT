package com.example.productcrud2.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="PRODUCT")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String desc;
    private double price;
}
