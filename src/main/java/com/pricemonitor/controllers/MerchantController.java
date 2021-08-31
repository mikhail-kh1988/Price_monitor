package com.pricemonitor.controllers;

import com.pricemonitor.dto.*;
import com.pricemonitor.entity.Category;
import com.pricemonitor.entity.Merchant;
import com.pricemonitor.entity.Price;
import com.pricemonitor.entity.Product;
import com.pricemonitor.service.CategoryService;
import com.pricemonitor.service.MerchantService;
import com.pricemonitor.service.ProductService;
import com.pricemonitor.tools.JSONConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/merchant")
public class MerchantController {

    @Autowired
    private MerchantService merchantService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

    @GetMapping("/all")
    public String getAllMerchant(){
        JSONConverter converter = new JSONConverter(merchantService.getAllMerchant());
        return converter.getJSON();
    }

    @PostMapping(path = "/addMerchant")
    public String crateNewMerchant(@RequestBody NewMerchantDTO dto){
        Merchant merchant = new Merchant();
        merchant.setName(dto.getMerchantName());
        merchant.setAddress(dto.getMerchantAddress());
        merchantService.createNewMerchant(merchant);
        return "success!";
    }

    @PostMapping(path = "/productsByMerchant")
    public String getProductByMerchant(@RequestBody MerchantDTO dto){
        Merchant merchant = merchantService.findMerchantById(dto.getID());
        java.util.List<Product> list = merchantService.getAllProductByMerchant(merchant);
        JSONConverter converter = new JSONConverter(list);
        return converter.getJSON();
    }

    @PostMapping(path = "/categoriesByMerchant")
    public String getCategoryByMerchant(@RequestBody MerchantDTO dto){
        Merchant merchant = merchantService.findMerchantById(dto.getID());
        java.util.List<Category> list = merchantService.getAllCategoryByMerchant(merchant);
        JSONConverter converter = new JSONConverter(list);
        return converter.getJSON();
    }

    @PostMapping(path = "/addProduct")
    public String addNewProduct(@RequestBody ProductForMerchantDTO dto){
        Product product = new Product();
        Price price = new Price();
        Category category = categoryService.findCategoryById(dto.getCategoryID());
        Merchant merchant = merchantService.findMerchantById(dto.getMerchantID());

        price.setMoney(dto.getMonyName());
        price.setTotal(dto.getTotalSum());

        product.setPrice(price);
        product.setCategory(category);
        product.setName(dto.getNameProduct());
        product.setBoxing(dto.getBoxing());

        merchantService.addNewProduct(merchant, product);
        return "success!";
    }

    @PostMapping(path = "/addCategory")
    public String addNewCategory(@RequestBody CategoryForMerchantDTO dto){
        Category category = new Category();
        category.setName(dto.getCategoryName());
        category.setDescription(dto.getCategoryDescription());

        Merchant merchant = merchantService.findMerchantById(dto.getMerchantID());

        merchantService.addNewCategory(merchant, category);
        return "success!";
    }
}
