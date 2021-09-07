package com.pricemonitor.service;

import com.pricemonitor.dto.CategoryDTO;
import com.pricemonitor.dto.CategoryForMerchantDTO;
import com.pricemonitor.dto.CategoryLinkedDTO;
import com.pricemonitor.entity.Category;
import com.pricemonitor.entity.Merchant;
import com.pricemonitor.repositories.ICategoryRepository;
import com.pricemonitor.repositories.impl.CategoryRepository;
import com.pricemonitor.tools.LoggerInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CategoryService {

    @Autowired
    private LoggerInfo logger;

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
    }

    public void deleteCategory(CategoryDTO dto){
        Category category = findCategoryByName(dto.getCategoryName());
        categoryRepository.deleteCategory(category);
    }

    public java.util.List<Category> getAllCategories(){
        return categoryRepository.getAllCategory();
    }

    public Category findCategoryById(int id){
        return categoryRepository.findCategoryById(id);
    }

    public Category findCategoryByName(String categoryName){
        return categoryRepository.findCategoryByName(categoryName);
    }

    public void addNewCategoryForMerchant(CategoryForMerchantDTO dto){
        Category category = new Category();
        category.setName(dto.getCategoryName());
        category.setDescription(dto.getCategoryDescription());

        Merchant merchant = merchantService.findMerchantById(dto.getMerchantID());

        merchantService.addNewCategory(merchant, category);
    }

    public void linkedCategoryForMerchant(CategoryLinkedDTO dto){
        Merchant merchant = merchantService.findMerchantById(dto.getMerchantId());
        Category category = findCategoryById(dto.getCategoryId());
        merchant.addCategory(category);
        merchantService.updateMerchant(merchant);
    }

}
