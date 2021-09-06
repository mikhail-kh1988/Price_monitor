package com.pricemonitor.service;

import com.pricemonitor.entity.*;
import com.pricemonitor.repositories.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Transient;
import java.util.Calendar;

@Service
public class ProductService {

    @Autowired
    private IProductRepository productRepository;


    public void createProduct(Product product){
        productRepository.createProduct(product);
    }

    public void updateProduct(Product product){
        productRepository.updateProduct(product);
    }

    public void deleteProduct(Product product){
        productRepository.removeProduct(product);
    }

    public Product findProductById(int id){
        return productRepository.findProductById(id);
    }

    public java.util.List<Product> getAllProducts(){
        return productRepository.findAllProduct();
    }

    public java.util.List<Product> findProductByCategory(Category category){
        return productRepository.findProductByCategory(category);
    }

    public java.util.List<Product> findProductByBoxing(String boxing){
        return productRepository.findProductByBoxing(boxing);
    }

}
