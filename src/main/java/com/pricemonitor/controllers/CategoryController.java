package com.pricemonitor.controllers;

import com.pricemonitor.dto.CategoryDTO;
import com.pricemonitor.entity.Category;
import com.pricemonitor.service.CategoryService;
import com.pricemonitor.tools.JSONConverter;
import com.pricemonitor.tools.LoggerInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private LoggerInfo logger;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/all")
    public String getAllCategories(){
        JSONConverter converter = new JSONConverter(categoryService.getAllCategories());
        return converter.getJSON();
    }

    @PostMapping(path = "/addCategory")
    public String addNewCategory(@RequestBody CategoryDTO categoryDTO){
        Category category = new Category();
        category.setName(categoryDTO.getCategoryName());
        category.setDescription(categoryDTO.getDescription());
        categoryService.createNewCategory(category);
        logger.info("["+this.getClass().getCanonicalName()+"] Вызван контроллер создания категории.");
        return "success!";
    }
}
