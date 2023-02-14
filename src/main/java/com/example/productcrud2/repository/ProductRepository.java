package com.example.productcrud2.repository;

import com.example.productcrud2.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {


    public Product findByIdAndName(int id, String name);
}
