package com.example.productcrud2.service;

import com.example.productcrud2.entity.Product;
import com.example.productcrud2.exception.ServiceException;
import com.example.productcrud2.repository.ProductRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class ProductService implements ProductServiceInterface{

    @Autowired
    private ProductRepository repository;


    //for initiating in memory database for pagination and sorting

    @PostConstruct
    public void initDB(){
        List<Product> products = IntStream.rangeClosed(1, 200)
                .mapToObj(i -> {
                    return new Product("name" + new Random().nextInt(5000), "desc" + new Random().nextInt(100), new Random().nextInt(1000000) + 1);
                })
                .collect(Collectors.toList());
        repository.saveAll(products);
    }


    //use case 1
    //to get all products
    @Override
    public List<Product> getAll(){
        return repository.findAll();
    }


    //get list of products after sorting
    @Override
    public List<Product> getAllSorted(String field){
        return repository.findAll(Sort.by(Sort.Direction.ASC, field));
    }


    //get list of products - pagination example
    @Override
    public Page<Product> getAllPagination(int pageSize, int offset){
        Page<Product>  products = repository.findAll(PageRequest.of(offset, pageSize));
        return products;

    }



    //get list of students with pagination and sorting
    @Override
    public Page<Product> getProductsWithPaginationAndSorting(int pageSize, int offset, String field){
        Page<Product> products = repository.findAll(PageRequest.of(offset, pageSize).withSort(Sort.Direction.ASC, field));
        return products;

    }


    //use case 2
    //get product by id and name
    @Override
    public Product getProductByIdName(int id, String name){
//        Product productById;
//        productById = repository.findById(id).get();
//        if(productById.getName().equals(name)){
//            return productById;
//        }
//        return null;
        Product product = repository.findByIdAndName(id, name);
        return product;


    }



    //use case 3
    //add a single product

    @Override
    public Product addProduct(Product product){
        Product savedProduct = repository.save(product);
        return savedProduct;
    }


    // use case 4
    // update product by id
    @Override
    public Product updateById(Product product, int id){
        Product checkProduct = repository.findById(id).get();
        checkProduct.setName(product.getName());
        checkProduct.setDesc(product.getDesc());
        checkProduct.setPrice(product.getPrice());
        Product updatedProduct = repository.save(checkProduct);
        return updatedProduct;

    }





    //use case 5
    //delete methods
    @Override
    public void deleteById(int id){
        repository.deleteById(id);
    }



}
