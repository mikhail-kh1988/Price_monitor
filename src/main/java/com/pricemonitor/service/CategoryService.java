package com.pricemonitor.service;

import com.pricemonitor.dto.CategoryDTO;
import com.pricemonitor.dto.CategoryForMerchantDTO;
import com.pricemonitor.dto.CategoryLinkedDTO;
import com.pricemonitor.entity.Category;
import com.pricemonitor.entity.Merchant;
import com.pricemonitor.repository.ICategoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MerchantService merchantService;

    @Autowired
    private ICategoryRepository categoryRepository;

    public void createNewCategory(CategoryDTO dto){
        Category category = new Category();
        category.setName(dto.getCategoryName());
        category.setDescription(dto.getDescription());

        categoryRepository.createCategory(category);
        logger.info("["+this.getClass().getCanonicalName()+"] Вызван сервис создания категории.");
    }

    public void updateCategory(CategoryDTO dto){
        Category category = findCategoryByName(dto.getCategoryName());
        category.setName(dto.getNewName());
        category.setDescription(dto.getNewDescription());

        categoryRepository.updateCategory(category);
        logger.info("["+this.getClass().getCanonicalName()+"] Вызван метод обновления категории "+category.getName());
    }

    public void deleteCategory(CategoryDTO dto){
        Category category = findCategoryByName(dto.getCategoryName());
        categoryRepository.deleteCategory(category);
        logger.info("["+this.getClass().getCanonicalName()+"] Вызван метод удаления категории "+category.getName());
    }

    public java.util.List<Category> getAllCategories(){
        logger.info("["+this.getClass().getCanonicalName()+"] Вызван метод возврата всех категорий.");
        return categoryRepository.getAllCategory();
    }

    public Category findCategoryById(int id){
        logger.info("["+this.getClass().getCanonicalName()+"] Вызван метод поиска категории по id = "+id);
        return categoryRepository.findCategoryById(id);
    }

    public Category findCategoryByName(String categoryName){
        logger.info("["+this.getClass().getCanonicalName()+"] Вызван поиск категории по имени. "+categoryName);
        return categoryRepository.findCategoryByName(categoryName);
    }

    public void addNewCategoryForMerchant(CategoryForMerchantDTO dto){
        Category category = new Category();
        category.setName(dto.getCategoryName());
        category.setDescription(dto.getCategoryDescription());

        Merchant merchant = merchantService.findMerchantById(dto.getMerchantID());

        merchantService.addNewCategory(merchant, category);
        logger.info("["+this.getClass().getCanonicalName()+"] Вызван метод добавления катеогории для продавца.");
    }

    public void linkedCategoryForMerchant(CategoryLinkedDTO dto){
        Merchant merchant = merchantService.findMerchantById(dto.getMerchantId());
        Category category = findCategoryById(dto.getCategoryId());
        merchant.addCategory(category);
        merchantService.updateMerchant(merchant);
        logger.info("["+this.getClass().getCanonicalName()+"] Вызван метод связи категории и магазина.");
    }

}
