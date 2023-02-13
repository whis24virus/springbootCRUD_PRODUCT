package com.example.productcrud2.service;

import com.example.productcrud2.entity.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductServiceInterface {
    public List<Product> getAll();
    public List<Product> getAllSorted(String field);
    public Page<Product> getAllPagination(int pageSize, int offset);
    public Page<Product> getProductsWithPaginationAndSorting(int pageSize, int offset, String field);
    public Product getProductByIdName(int id, String name);
    public Product addProduct(Product product);
    public Product updateById(Product product, int id);
    public void deleteById(int id);
}
