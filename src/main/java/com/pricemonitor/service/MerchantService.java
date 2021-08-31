package com.pricemonitor.service;

import com.pricemonitor.entity.Category;
import com.pricemonitor.entity.Merchant;
import com.pricemonitor.entity.Product;
import com.pricemonitor.entity.RatingPriceByMerchant;
import com.pricemonitor.repositories.IMerchantRepository;
import com.pricemonitor.repositories.IRatingPriceByMerchantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MerchantService {

    @Autowired
    private IMerchantRepository merchantRepository;

    @Autowired
    private IRatingPriceByMerchantRepository ratingPriceByMerchantRepository;

    public void createNewMerchant(Merchant merchant){
        merchantRepository.createMerchant(merchant);
    }

    public void updateMerchant(Merchant merchant){
        merchantRepository.updateMerchant(merchant);
    }

    public void deleteMerchant(Merchant merchant){
        merchantRepository.deleteMerchant(merchant);
    }

    public Merchant findMerchantById(int id){
        return merchantRepository.findMerchantById(id);
    }

    public java.util.List<Merchant> getAllMerchant(){
        return merchantRepository.findAllMerchant();
    }

    public void addNewProduct(Merchant merchant, Product product){
        java.util.List<Product> productList = new ArrayList<>();
        productList.add(product);
        merchant.setProductList(productList);
        merchantRepository.updateMerchant(merchant);

        RatingPriceByMerchant ratingPriceByMerchant = new RatingPriceByMerchant();
        ratingPriceByMerchant.setMerchant(merchant);
        ratingPriceByMerchant.setPrice(product.getPrice());
        ratingPriceByMerchant.setCategory(product.getCategory());
        ratingPriceByMerchant.setProduct(product);
        ratingPriceByMerchantRepository.createRatingPrice(ratingPriceByMerchant);
    }

    public void addNewCategory(Merchant merchant, Category category){
        java.util.List<Category> categoryList = new ArrayList<>();
        categoryList.add(category);
        merchant.setCategoryList(categoryList);
        merchantRepository.updateMerchant(merchant);
    }

    public java.util.List<Product> getAllProductByMerchant(Merchant merchant){
        Merchant merchant1 = merchantRepository.findMerchantById(merchant.getId());
        java.util.List<Product> productList = merchant1.getProductList();
        return productList;
    }

    public java.util.List<Category> getAllCategoryByMerchant(Merchant merchant){
        Merchant merchant1 = merchantRepository.findMerchantById(merchant.getId());
        java.util.List<Category> categoryList = merchant1.getCategoryList();
        return categoryList;
    }
}
