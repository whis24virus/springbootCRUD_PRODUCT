package com.example.productcrud2.controller;

import com.example.productcrud2.entity.Product;
import com.example.productcrud2.exception.ControllerException;
import com.example.productcrud2.exception.ServiceException;
import com.example.productcrud2.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/demo")
public class ProductController {
    @Autowired
    private ProductService service;

    //use case 1
    // get all products
    @GetMapping("/products")
    public List<Product> findAll(){
        return service.getProducts();
    }





    //use case 2
    //get product by id and name
    @GetMapping("/products/{id}/{name}")
    public Product findByIdNAME(@PathVariable int id, @PathVariable String name){
        return service.getProductByIdName(id, name);
    }





    //use cse 3
    //add single product
    @PostMapping("/product")
    public Product addProduct(@RequestBody Product product){
//        try{
//            return service.saveProduct(product);
//        }catch (ServiceException e){
//            throw new ControllerException(e.getErrorCode(), e.getErrorMessage());
//
//        }
//        catch (Exception e){
//            throw new ControllerException("611","Something went wrong in controller");
//
//        }
        return service.saveProduct(product);
    }


    //update product by id
    //use case 4
    @PutMapping("/product/{id}")
    public Product updateProductById(@RequestBody Product product, @PathVariable int id){
        return service.updateProductById(product, id);
    }




    //use case 5
    //delete
    @DeleteMapping(value = {"delete/{id}", "product/{id}"})
    public String deleteProduct(@PathVariable int id){
        return service.deleteProduct(id);
    }

}
