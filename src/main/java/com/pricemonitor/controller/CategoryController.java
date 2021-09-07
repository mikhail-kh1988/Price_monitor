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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> getAllCategories(){
        JSONConverter converter = new JSONConverter(categoryService.getAllCategories());
        return new ResponseEntity<>(converter.getJSON(), HttpStatus.OK);
    }

    @PostMapping(path = "/addCategory")
    public ResponseEntity<String> addNewCategory(@RequestBody CategoryDTO dto){
        categoryService.createNewCategory(dto);
        logger.info("["+this.getClass().getCanonicalName()+"] Вызван контроллер создания категории.");
        return new ResponseEntity<>("success!", HttpStatus.OK);
    }

    @PostMapping(path = "/updateCategory")
    public ResponseEntity<String> updateCategory(@RequestBody CategoryDTO dto){
        categoryService.updateCategory(dto);
        return new ResponseEntity<>("success!", HttpStatus.OK);
    }

    @PostMapping(path = "/deleteCategory")
    public ResponseEntity<String> deleteCategory(@RequestBody CategoryDTO dto){
        categoryService.deleteCategory(dto);
        return new ResponseEntity<>("success!", HttpStatus.OK);
    }

    @PostMapping(path = "/addCategoryForMerchant")
    public ResponseEntity<String> addNewCategory(@RequestBody CategoryForMerchantDTO dto){
        categoryService.addNewCategoryForMerchant(dto);
        return new ResponseEntity<>("success!", HttpStatus.OK);
    }

    @PostMapping(path = "/linkedCategoryMerchant")
    public ResponseEntity<String> linkedCategory(@RequestBody CategoryLinkedDTO dto){
        categoryService.linkedCategoryForMerchant(dto);
        return new ResponseEntity<>("success!", HttpStatus.OK);
    }

    @PostMapping(path = "/categoriesByMerchant")
    public ResponseEntity<String> getCategoryByMerchant(@RequestBody MerchantDTO dto){
        Merchant merchant = merchantService.findMerchantById(dto.getID());
        java.util.List<Category> list = merchantService.getAllCategoryByMerchant(merchant);
        JSONConverter converter = new JSONConverter(list);
        return new ResponseEntity<>(converter.getJSON(), HttpStatus.OK);
    }
}
