package com.pricemonitor.controller;

import com.pricemonitor.dto.CategoryDTO;
import com.pricemonitor.dto.MerchantDTO;
import com.pricemonitor.dto.ProductDTO;
import com.pricemonitor.entity.Category;
import com.pricemonitor.entity.Merchant;
import com.pricemonitor.entity.Product;
import com.pricemonitor.service.*;
import com.pricemonitor.tools.JSONConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/monitoring")
public class MonitoringController {

    @Autowired
    private MerchantService merchantService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

/*
    @PostMapping(path = "/getRating2Merchant")
    public String getRatingPriceBy2Merchant(@RequestBody Rating2MerchantDTO dto){
        Merchant merchant1 = merchantService.findMerchantById(dto.getMerchantID1());
        Merchant merchant2 = merchantService.findMerchantById(dto.getMerchantID2());
        JSONConverter converter = new JSONConverter(ratingPriceByMerchantService.getListRatingBetweenMerchant(merchant1, merchant2));
        return converter.getJSON();
    }

    @PostMapping(path = "/getRatingByCategory")
    public String getRatingPriceByCategory(@RequestBody CategoryDTO dto){
        Category category = categoryService.findCategoryByName(dto.getCategoryName());
        JSONConverter converter = new JSONConverter(ratingPriceByMerchantService.findRatingByCategory(category));
        return converter.getJSON();
    }

    @PostMapping(path = "/getRatingByMerchant")
    public String getRatingPriceByMerchant(@RequestBody MerchantDTO dto){
        Merchant merchant = merchantService.findMerchantById(dto.getID());
        JSONConverter converter = new JSONConverter(ratingPriceByMerchantService.findRatingByMerchant(merchant));
        return converter.getJSON();
    }

    @PostMapping(path = "/getRatingByProduct")
    public String getRatingPriceByProduct(@RequestBody ProductDTO dto){
        Product product = productService.findProductById(dto.getProductID());
        JSONConverter converter = new JSONConverter(ratingPriceByMerchantService.findRatingByProduct(product));
        return converter.getJSON();
    }

    @GetMapping("/getAllRatingByMerchantList")
    public String getAllRatingByMerchantList(){
        JSONConverter converter = new JSONConverter(ratingPriceByMerchantService.getAllRatingPriceByMerchantList());
        return converter.getJSON();
    }*/

}
