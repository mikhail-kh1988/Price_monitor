package com.pricemonitor.controller;

import com.pricemonitor.dto.CategoryDTO;
import com.pricemonitor.dto.CategoryForMerchantDTO;
import com.pricemonitor.dto.CategoryLinkedDTO;
import com.pricemonitor.dto.MerchantDTO;
import com.pricemonitor.entity.Category;
import com.pricemonitor.entity.Merchant;
import com.pricemonitor.service.CategoryService;
import com.pricemonitor.service.MerchantService;
import com.pricemonitor.tools.JSONConverter;
import com.pricemonitor.tools.LoggerInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
public class CategoryController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private MerchantService merchantService;

    @GetMapping("/all")
    public String getAllCategories(){
        JSONConverter converter = new JSONConverter(categoryService.getAllCategories());
        return converter.getJSON();
    }

    @PostMapping(path = "/addCategory")
    public String addNewCategory(@RequestBody CategoryDTO dto){
        Category category = new Category();
        category.setName(dto.getCategoryName());
        category.setDescription(dto.getDescription());
        categoryService.createNewCategory(category);
        logger.info("["+this.getClass().getCanonicalName()+"] Вызван контроллер создания категории.");
        return "success!";
    }

    @PostMapping(path = "/updateCategory")
    public String updateCategory(@RequestBody CategoryDTO dto){
        Category category = categoryService.findCategoryByName(dto.getCategoryName());
        category.setName(dto.getNewName());
        category.setDescription(dto.getNewDescription());
        categoryService.updateCategory(category);
        return "success!";
    }

    @PostMapping(path = "/deleteCategory")
    public String deleteCategory(@RequestBody CategoryDTO dto){
        Category category = categoryService.findCategoryByName(dto.getCategoryName());
        categoryService.deleteCategory(category);
        return "success!";
    }

    @PostMapping(path = "/addCategoryForMerchant")
    public String addNewCategory(@RequestBody CategoryForMerchantDTO dto){
        Category category = new Category();
        category.setName(dto.getCategoryName());
        category.setDescription(dto.getCategoryDescription());
        //categoryService.createNewCategory(category);

        Merchant merchant = merchantService.findMerchantById(dto.getMerchantID());

        merchantService.addNewCategory(merchant, category);
        return "success!";
    }

    @PostMapping(path = "/linkedCategoryMerchant")
    public String linkedCategory(@RequestBody CategoryLinkedDTO dto){
        Merchant merchant = merchantService.findMerchantById(dto.getMerchantId());
        Category category = categoryService.findCategoryById(dto.getCategoryId());
        merchant.addCategory(category);
        merchantService.updateMerchant(merchant);
        return "success!";
    }

    @PostMapping(path = "/categoriesByMerchant")
    public String getCategoryByMerchant(@RequestBody MerchantDTO dto){
        Merchant merchant = merchantService.findMerchantById(dto.getID());
        java.util.List<Category> list = merchantService.getAllCategoryByMerchant(merchant);
        JSONConverter converter = new JSONConverter(list);
        return converter.getJSON();
    }
}
