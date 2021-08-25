package com.pricemonitor.controllers;

import com.pricemonitor.dto.MerchantDTO;
import com.pricemonitor.dto.ProductForMerchantDTO;
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

    @PostMapping(path = "/productsByMerchant")
    public String getProductByMerchant(@RequestBody MerchantDTO merchantDTO){
        Merchant merchant = new Merchant();
        merchant.setId(merchantDTO.getID());
        merchant.setName(merchantDTO.getName());
        java.util.List<Product> list = merchantService.getAllProductByMerchant(merchant);
        JSONConverter converter = new JSONConverter(list);
        return converter.getJSON();
    }

    @PostMapping(path = "/categoriesByMerchant")
    public String getCategoryByMerchant(@RequestBody MerchantDTO merchantDTO){
        Merchant merchant = new Merchant();
        merchant.setName(merchantDTO.getName());
        merchant.setId(merchantDTO.getID());
        java.util.List<Category> list = merchantService.getAllCategoryByMerchant(merchant);
        JSONConverter converter = new JSONConverter(list);
        return converter.getJSON();
    }

    @PostMapping(path = "/addProduct")
    public String addNewProduct(@RequestBody ProductForMerchantDTO productForMerchantDTO){
        Product product = new Product();
        Price price = new Price();
        Category category = categoryService.findCategoryById(productForMerchantDTO.getCategoryID());
        Merchant merchant = merchantService.findMerchantById(productForMerchantDTO.getMerchantID());

        price.setMoney(productForMerchantDTO.getMonyName());
        price.setTotal(productForMerchantDTO.getTotalSum());

        product.setPrice(price);
        product.setCategory(category);
        product.setName(productForMerchantDTO.getNameProduct());
        product.setBoxing(productForMerchantDTO.getBoxing());

        merchantService.addNewProduct(merchant, product);
        return "success!";
    }

}
