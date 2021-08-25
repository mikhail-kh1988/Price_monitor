package com.pricemonitor.service;

import com.pricemonitor.entity.Category;
import com.pricemonitor.repositories.ICategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    @Autowired
    private ICategoryRepository categoryRepository;

    public void createNewCategory(Category category){
        categoryRepository.createCategory(category);
    }

    public java.util.List<Category> getAllCategories(){
        return categoryRepository.getAllCategory();
    }

    public Category findCategoryById(int id){
        return categoryRepository.findCategoryById(id);
    }

}
