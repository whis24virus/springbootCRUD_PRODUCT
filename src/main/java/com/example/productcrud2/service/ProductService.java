package com.example.productcrud2.service;

import com.example.productcrud2.entity.Product;
import com.example.productcrud2.exception.ServiceException;
import com.example.productcrud2.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;



    //use case 1
    //to get all products
    public List<Product> getProducts(){
        try{
            List<Product> productList = repository.findAll();
            if(productList.isEmpty()){
                throw new ServiceException("604", "List of Products is Empty, There is nothing to Return");

            }
            return productList;
        }
        catch(Exception e){
            throw new ServiceException("605", "Something went wrong in service layer while fetching all employees "+ e.getMessage());
        }
//         return repository.findAll();

    }



    //use case 2
    //get product by id and name
    public Product getProductByIdName(int id, String name){
        Product productById;
        try{
            //System.out.println("1");
            productById = repository.findById(id).get();
        }
        catch(IllegalArgumentException e){
           // System.out.println("1.2");
            throw new ServiceException("606", "given employee id is null, please send a vaild input" + e.getMessage());

        }
        catch(NoSuchElementException e){
           // System.out.println("1.3");
            throw new ServiceException("607", "given id does not exist in the database"+ e.getMessage());
        }
        try{
           // System.out.println("2");
            if(name.isEmpty() || name.length() == 0){
               // System.out.println("2.1");
                throw new ServiceException("601", "Please enter a valid name, It is currently black");
            }
            if(productById.getName().equals(name)){
                System.out.println("2.2");
                return productById;
            }
            //System.out.println("2.3");
            throw new ServiceException("611", "Given id and name combination does not exist");

        }
        catch(Exception e){
            //System.out.println("3");
            throw new ServiceException("603", "Something went wrong in the Service layer"+ e.getMessage());
        }
//        return repository.findById(id).get();


    }



    //use case 3
    //add a single product

    public Product saveProduct(Product product){
        try{
            //System.out.println("save product 1");
            if(product.getName().isEmpty() || product.getName().length() == 0){
                System.out.println("save product 1.1");
                throw new ServiceException("601", "Please enter a valid name, It is currently black");
            }
            //System.out.println("save product 1.2");
            return repository.save(product);
//            return product;

        }
        catch(IllegalArgumentException e){
            //System.out.println("save product 2");
            throw new ServiceException("602", "given product is null" + e.getMessage());

        }
        catch(Exception e){
            //System.out.println("save product 3");
            throw new ServiceException("603", "Something went wrong in the Service layer"+ e.getMessage());
        }
        //return repository.save(product);
    }


    // use case 4
    // update product by id
    public Product updateProductById(Product product, int id){
        try{
            Product existingProduct = repository.findById(id).orElse(null);
            if(existingProduct == null){
                throw new ServiceException("601","Product By this ID does not exist");
            }
            if(product.getName() == null){
                throw new ServiceException("601","Please enter a valid NAME");
            }
            existingProduct.setName(product.getName());

            if(product.getPrice() == 0){
                throw new ServiceException("601","Please enter a valid PRICE ");
            }
            existingProduct.setPrice(product.getPrice());

            if(product.getDesc() == null){
                throw new ServiceException("601","Please enter a valid DESCRIPTION***");
            }
            existingProduct.setDesc(product.getDesc());

            return repository.save(existingProduct);
        }
        catch(Exception e){
            throw new ServiceException("601", "Exception in service layer");
        }

    }





    //use case 5
    //delete methods
    public String deleteProduct(int id){
        try{
            repository.deleteById(id);
            return "product removed with the id -----> " + id+" ******";
        }
        catch(IllegalArgumentException e){
            throw new ServiceException("608", "given employee id is null, please send a vaild input" + e.getMessage());

        }
        catch(NoSuchElementException e){
            throw new ServiceException("609", "given id does not exist in the database"+ e.getMessage());
        }
        catch(Exception e){
            throw new ServiceException("601","Exception in Service Layer"+e.getMessage());
        }

    }



}
