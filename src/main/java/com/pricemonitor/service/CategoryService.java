package com.pricemonitor.service;

import com.pricemonitor.entity.Category;
import com.pricemonitor.repositories.ICategoryRepository;
import com.pricemonitor.tools.LoggerInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    @Autowired
    private LoggerInfo logger;

    @Autowired
    private ICategoryRepository categoryRepository;

    public void createNewCategory(Category category){
        categoryRepository.createCategory(category);
        logger.info("["+this.getClass().getCanonicalName()+"] Вызван сервис создания категории.");
    }

    public java.util.List<Category> getAllCategories(){
        return categoryRepository.getAllCategory();
    }

    public Category findCategoryById(int id){
        return categoryRepository.findCategoryById(id);
    }

}
