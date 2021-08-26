package com.pricemonitor.controllers;

import com.pricemonitor.dto.ProductDTO;
import com.pricemonitor.entity.Category;
import com.pricemonitor.entity.Price;
import com.pricemonitor.entity.Product;
import com.pricemonitor.service.CategoryService;
import com.pricemonitor.service.ProductService;
import com.pricemonitor.tools.JSONConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/all")
    public String getAllProduct(){
        JSONConverter converter = new JSONConverter(productService.getAllProducts());
        return converter.getJSON();
    }

    @PostMapping(path = "/addProduct")
    public String createNewProduct(@RequestBody ProductDTO productDTO){
        Product product = new Product();
        Category category = categoryService.findCategoryById(productDTO.getCategoryID());
        Price price = new Price();
        price.setMoney("RUB");
        price.setTotal(productDTO.getPrice());
        product.setName(productDTO.getName());
        product.setPrice(price);
        product.setCategory(category);
        product.setBoxing(productDTO.getBoxing());
        productService.createProduct(product);
        return "success!";
    }

    @PostMapping(path = "/updateProduct")
    public String updateProduct(@RequestBody ProductDTO productDTO){
        Product product = productService.findProductById(productDTO.getProductID());
        product.setName(productDTO.getName());
        product.setBoxing(productDTO.getBoxing());
        product.setCategory(categoryService.findCategoryById(productDTO.getCategoryID()));
        Price price = new Price();
        price.setTotal(productDTO.getPrice());
        price.setMoney("RUB");
        product.setPrice(price);
        productService.updateProduct(product);
        return "success!";
    }

    @PostMapping(path = "/deleteProduct")
    public String deleteProduct(@RequestBody ProductDTO productDTO){
        Product product = productService.findProductById(productDTO.getProductID());
        productService.deleteProduct(product);
        return "success";

    }



}
