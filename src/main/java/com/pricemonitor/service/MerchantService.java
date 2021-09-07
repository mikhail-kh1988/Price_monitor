package com.pricemonitor.service;

import com.pricemonitor.dto.MerchantDTO;
import com.pricemonitor.dto.NewMerchantDTO;
import com.pricemonitor.entity.Category;
import com.pricemonitor.entity.Merchant;
import com.pricemonitor.entity.Product;
import com.pricemonitor.repositories.ICategoryRepository;
import com.pricemonitor.repositories.IMerchantRepository;
import com.pricemonitor.repositories.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MerchantService {

    @Autowired
    private IMerchantRepository merchantRepository;

    @Autowired
    private ICategoryRepository categoryRepository;

    @Autowired
    private IProductRepository productRepository;


    public void createNewMerchant(NewMerchantDTO dto){
        Merchant merchant = new Merchant();
        merchant.setName(dto.getMerchantName());
        merchant.setAddress(dto.getMerchantAddress());
        merchantRepository.createMerchant(merchant);
    }

    public void updateMerchant(MerchantDTO dto){
        Merchant merchant = findMerchantById(dto.getID());
        merchant.setName(dto.getNewName());
        merchant.setAddress(dto.getNewAddress());
        merchantRepository.updateMerchant(merchant);
    }

    public void updateMerchant(Merchant merchant){
        merchantRepository.updateMerchant(merchant);
    }

    public void deleteMerchant(MerchantDTO dto){
        Merchant merchant = findMerchantById(dto.getID());
        merchantRepository.deleteMerchant(merchant);
    }

    public Merchant findMerchantById(int id){
        return merchantRepository.findMerchantById(id);
    }

    public java.util.List<Merchant> getAllMerchant(){
        return merchantRepository.findAllMerchant();
    }

    public void addNewProduct(Merchant merchant, Product product){
        java.util.List<Product> productList = merchantRepository.findMerchantById(merchant.getId()).getProductList();
        productList.add(product);
        merchant.setProductList(productList);
        merchantRepository.updateMerchant(merchant);
    }

    public void addNewCategory(Merchant merchant, Category category){
        categoryRepository.createCategory(category);
        java.util.List<Category> categoryList = merchantRepository.findMerchantById(merchant.getId()).getCategoryList();
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
