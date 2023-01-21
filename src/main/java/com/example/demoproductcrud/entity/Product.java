package com.example.demoproductcrud.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="PRODUCT")
public class Product {

    @Id
    @GeneratedValue
    @NotNull
    private int id;
    @NotNull
    @Digits(integer=7,fraction=0)
    private String name;
    @NotNull
    private String desc;
    @NotNull
    @Digits(integer=8,fraction=3)
    private double price;
}
