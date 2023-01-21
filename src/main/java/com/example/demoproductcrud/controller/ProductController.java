package com.example.demoproductcrud.controller;


import com.example.demoproductcrud.entity.Product;
import com.example.demoproductcrud.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/demo")
public class ProductController {

    @Autowired
    private ProductService service;


    // rest endpoints





    //post method endpoints


    //add single product
    @PostMapping("/addProduct")
    public Product addProduct(@RequestBody Product product){
        return service.saveProduct(product);
    }

    //add all products

    @PostMapping("/addProducts")
    public List<Product> addProducts(@RequestBody List<Product> products){
        return service.saveProducts(products);
    }



    //get method endpoints



    //get all products
    @GetMapping("/products")
    public List<Product> findAll(){
        return service.getProducts();
    }


    //get product by id
    @GetMapping("/products/{id}")
    public Optional<Product> findProductId(@PathVariable int id){
        return service.getProductById(id);
    }


    //get product by name

    @GetMapping("/products/{name}")
    public List<Product> findByName(@PathVariable String name){
        return service.getProductByName(name);
    }


    //get product id and product name by name

    @GetMapping("/products/{id}/{name}")
    public Optional<Product> findByIDNAME(@PathVariable int id, @PathVariable String name){
        return service.getProductByIdName(id, name);
    }



    //put endpoint



    //update product
    @PutMapping("/product/update")
    public Product updateProduct(@RequestBody Product product){
        return service.updateProduct(product);
    }

    //update product by id
    @PutMapping("/product/update/{id}")
    public Product updateProductById(@RequestBody Product product, @PathVariable int id){
        return service.updateProductById(product, id);
    }

    //delete endpoint


    //delete api

    @DeleteMapping(value = {"delete/{id}", "product/{id}"})
    public String deleteProduct(@PathVariable int id){
        return service.deleteProduct(id);
    }



}
