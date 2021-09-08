package com.pricemonitor.service;

import com.pricemonitor.dto.MerchantDTO;
import com.pricemonitor.dto.NewMerchantDTO;
import com.pricemonitor.entity.Category;
import com.pricemonitor.entity.Merchant;
import com.pricemonitor.entity.Product;
import com.pricemonitor.repository.ICategoryRepository;
import com.pricemonitor.repository.IMerchantRepository;
import com.pricemonitor.repository.IProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MerchantService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

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
        logger.info("["+this.getClass().getCanonicalName()+"] Вызван метод создания магазина.");
    }

    public void updateMerchant(MerchantDTO dto){
        Merchant merchant = findMerchantById(dto.getID());
        merchant.setName(dto.getNewName());
        merchant.setAddress(dto.getNewAddress());
        merchantRepository.updateMerchant(merchant);
        logger.info("["+this.getClass().getCanonicalName()+"] Вызван метод обновления магазина.");
    }

    public void updateMerchant(Merchant merchant){
        merchantRepository.updateMerchant(merchant);
        logger.info("["+this.getClass().getCanonicalName()+"] Вызван метод обновления магазина.");
    }

    public void deleteMerchant(MerchantDTO dto){
        Merchant merchant = findMerchantById(dto.getID());
        merchantRepository.deleteMerchant(merchant);
        logger.info("["+this.getClass().getCanonicalName()+"] Вызван метод удаления магазина. ");
    }

    public Merchant findMerchantById(int id){
        logger.info("["+this.getClass().getCanonicalName()+"] Вызван метод поиска магазина по ID = "+id);
        return merchantRepository.findMerchantById(id);
    }

    public java.util.List<Merchant> getAllMerchant(){
        logger.info("["+this.getClass().getCanonicalName()+"] Вызван метод возврата всех магазинов.");
        return merchantRepository.findAllMerchant();
    }

    public void addNewProduct(Merchant merchant, Product product){
        java.util.List<Product> productList = merchantRepository.findMerchantById(merchant.getId()).getProductList();
        productList.add(product);
        merchant.setProductList(productList);
        merchantRepository.updateMerchant(merchant);
        logger.info("["+this.getClass().getCanonicalName()+"] Вызван метод добавления продукта "+product.getName()+" в магазин.");
    }

    public void addNewCategory(Merchant merchant, Category category){
        categoryRepository.createCategory(category);
        java.util.List<Category> categoryList = merchantRepository.findMerchantById(merchant.getId()).getCategoryList();
        categoryList.add(category);
        merchant.setCategoryList(categoryList);
        merchantRepository.updateMerchant(merchant);
        logger.info("["+this.getClass().getCanonicalName()+"] Вызван метод добавления категории "+category.getName()+" в магазин "+merchant.getName());
    }

    public java.util.List<Product> getAllProductByMerchant(Merchant merchant){
        Merchant merchant1 = merchantRepository.findMerchantById(merchant.getId());
        java.util.List<Product> productList = merchant1.getProductList();
        logger.info("["+this.getClass().getCanonicalName()+"] Вызван метод полученич всех продуктов магазина "+merchant.getName());
        return productList;
    }

    public java.util.List<Category> getAllCategoryByMerchant(Merchant merchant){
        Merchant merchant1 = merchantRepository.findMerchantById(merchant.getId());
        java.util.List<Category> categoryList = merchant1.getCategoryList();
        logger.info("["+this.getClass().getCanonicalName()+"] Вызван метод получения списка всех категорий магазина "+merchant.getName());
        return categoryList;
    }
}
