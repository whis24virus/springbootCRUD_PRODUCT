package com.example.productcrud2.controller;

import com.example.productcrud2.entity.Product;
import com.example.productcrud2.exception.ControllerException;
import com.example.productcrud2.exception.ServiceException;
import com.example.productcrud2.service.ProductService;
import com.example.productcrud2.service.ProductServiceInterface;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/demo/products")
public class ProductController {
    @Autowired
    private ProductServiceInterface service;

    //use case 1
    // get all products
    @GetMapping("/all")
    public ResponseEntity<List<Product>> findAll(){
        List<Product> products = service.getAll();
        return new ResponseEntity<List<Product>>(products,HttpStatus.OK);
    }

    //get all users sorted by field
    @GetMapping("/all/{field}")
    public ResponseEntity<List<Product>> findAllSorting(@PathVariable String field){
        List<Product> products = service.getAllSorted(field);
        return new ResponseEntity<List<Product>>(products,HttpStatus.OK);
    }


    //get list of users by page

    @GetMapping("/all/pagination/{pagesize}/{offset}")
    public ResponseEntity<Page<Product>> getStudentByPage(@PathVariable int pagesize, @PathVariable int offset){
        Page<Product> productPage = service.getAllPagination(pagesize, offset);
        return new ResponseEntity<Page<Product>>(productPage, HttpStatus.OK);
    }


    //get list of users by page and by sorting useing a field

    @GetMapping("/all/{field}/pagination/{pagesize}/{offset}")
    public ResponseEntity<Page<Product>> getProductByPageAndSorting(@PathVariable int pagesize, @PathVariable int offset, @PathVariable String field){
        Page<Product> productPage = service.getProductsWithPaginationAndSorting(pagesize, offset, field);
        return new ResponseEntity<Page<Product>>(productPage, HttpStatus.OK);
    }


    //use case 2
    //get product by id and name
    @GetMapping("/{id}/{name}")
    public ResponseEntity<Product> findByIdNAME(@PathVariable int id, @PathVariable String name){
        Product product =  service.getProductByIdName(id, name);
        return new ResponseEntity<Product>(product, HttpStatus.OK);
    }





    //use cse 3
    //add single product
    @PostMapping("/save")
    public ResponseEntity<Product> addProduct(@RequestBody Product product){
        Product productSaved = service.addProduct(product);

        return new ResponseEntity<Product>(productSaved, HttpStatus.CREATED);
    }


    //update product by id
    //use case 4
    @PutMapping("/update/{id}")
    public ResponseEntity<Product> updateProductById(@Valid @RequestBody Product product, @PathVariable int id){
        Product updatedProduct;
        updatedProduct = service.updateById(product, id);
        return new ResponseEntity<Product>(updatedProduct, HttpStatus.OK);
    }




    //use case 5
    //delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable int id){
        service.deleteById(id);
        return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
    }

}
