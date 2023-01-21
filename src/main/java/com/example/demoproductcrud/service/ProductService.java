package com.example.demoproductcrud.service;

import com.example.demoproductcrud.entity.Product;
import com.example.demoproductcrud.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {


    @Autowired
    private ProductRepository repository;


    //post methods

    public Product saveProduct(Product product){
        return repository.save(product);
    }



    public List<Product> saveProducts(List<Product> products){
        return repository.saveAll(products);
    }


    //get methods
    public List<Product> getProducts(){
        return repository.findAll();

    }


    public Optional<Product> getProductById(int id){
        return repository.findById(id);

    }


    public List<Product> getProductByName(String name){
        return repository.findByName(name);

    }

    public Optional<Product> getProductByIdName(int id, String name){
        List<Product> listOfProductsByName = getProductByName(name);
        Optional<Product> finalProduct;
        if(listOfProductsByName != null){
        finalProduct = listOfProductsByName.stream().filter(x -> {if(x.getId() == id) return x;});
        }
        return finalProduct;

    }




    //delete methods

    public String deleteProduct(int id){
        repository.deleteById(id);
        return "product removed with the id -----> " + id+" ******";
    }


    // put / update methods

    public Product updateProduct(Product product){
        Product existingProduct = repository.findById(product.getId()).orElse(null);
        existingProduct.setName(product.getName());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setDesc(product.getDesc());
        return repository.save(existingProduct);
    }

    //update product by id
    public Product updateProductById(Product product, int id){
        Product existingProduct = repository.findById(id).orElse(null);
        existingProduct.setName(product.getName());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setDesc(product.getDesc());
        return repository.save(existingProduct);
    }


}
