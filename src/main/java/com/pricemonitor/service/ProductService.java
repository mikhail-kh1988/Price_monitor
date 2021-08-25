package com.pricemonitor.service;

import com.pricemonitor.entity.*;
import com.pricemonitor.repositories.IDynamicPriceRepository;
import com.pricemonitor.repositories.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;

@Service
public class ProductService {

    @Autowired
    private IProductRepository productRepository;

    @Autowired
    private IDynamicPriceRepository dynamicPriceRepository;

    public void createProduct(Product product){
        productRepository.createProduct(product);

        DynamicPrice dynamicPrice = new DynamicPrice();
        dynamicPrice.setPrice(product.getPrice());
        dynamicPrice.setProduct(product);
        dynamicPrice.setCreateDate(Calendar.getInstance());
        dynamicPriceRepository.createDynamicPrice(dynamicPrice);
    }

    public void updateProduct(Product product){
        Product current = productRepository.findProductById(product.getId());
        if (current.getPrice().getTotal() != product.getPrice().getTotal()) {
            DynamicPrice dynamicPrice = new DynamicPrice();
            dynamicPrice.setPrice(product.getPrice());
            dynamicPrice.setProduct(product);
            dynamicPrice.setCreateDate(Calendar.getInstance());
            dynamicPriceRepository.createDynamicPrice(dynamicPrice);
        }
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

    // Пока под вопросом.
    /*public void addPriceForProductByMerchant(Price price, Product product, Merchant merchant, Calendar currentDate){

    }*/

}
