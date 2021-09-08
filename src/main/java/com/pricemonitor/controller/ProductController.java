package com.pricemonitor.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pricemonitor.dto.*;
import com.pricemonitor.entity.*;
import com.pricemonitor.service.CategoryService;
import com.pricemonitor.service.MerchantService;
import com.pricemonitor.service.ProductService;
import com.pricemonitor.tools.JSONPackageLoadTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private MerchantService merchantService;

    @GetMapping("/all")
    public ResponseEntity<String> getAllProduct() throws JsonProcessingException{
        ObjectMapper mapper = new ObjectMapper();
        return new ResponseEntity<>(mapper.writeValueAsString(productService.getAllProducts()), HttpStatus.OK);
    }

    @PostMapping("/getProductByCategory")
    public ResponseEntity<String> getProductByCategoryName(@RequestBody CategoryDTO dto) throws JsonProcessingException{
        ObjectMapper mapper = new ObjectMapper();
        return new ResponseEntity<>(mapper.writeValueAsString(productService.findProductByCategory(dto)), HttpStatus.OK);
    }

    @PostMapping(path = "/addProduct")
    public ResponseEntity<String> createNewProduct(@RequestBody ProductDTO dto){
        productService.createProduct(dto);
        return new ResponseEntity<>("success!", HttpStatus.OK);
    }

    @PostMapping(path = "/updateProduct")
    public ResponseEntity<String> updateProduct(@RequestBody ProductDTO dto){
        productService.updateProduct(dto);
        return new ResponseEntity<>("success!", HttpStatus.OK);
    }

    @PostMapping(path = "/deleteProduct")
    public ResponseEntity<String> deleteProduct(@RequestBody ProductDTO dto){
        Product product = productService.findProductById(dto.getProductID());
        productService.deleteProduct(product);
        return new ResponseEntity<>("success", HttpStatus.OK);

    }

    @PostMapping(path = "/productsByMerchant")
    public ResponseEntity<String> getProductByMerchant(@RequestBody MerchantDTO dto) throws JsonProcessingException{
        Merchant merchant = merchantService.findMerchantById(dto.getID());
        java.util.List<Product> list = merchantService.getAllProductByMerchant(merchant);
        ObjectMapper mapper = new ObjectMapper();
        return new ResponseEntity<>(mapper.writeValueAsString(list), HttpStatus.OK);
    }

    @PostMapping(path = "/addProductForMerchant")
    public ResponseEntity<String> addNewProduct(@RequestBody ProductForMerchantDTO dto){
        productService.createProductForMerchant(dto);
        return new ResponseEntity<>("success!", HttpStatus.OK);
    }

    @PostMapping(path = "/linkedPriceProductWithMerchant")
    public ResponseEntity<String> linkedProductWithMerchant(@RequestBody PriceProductMerchantDTO dto){
        productService.linkedProductWithMerchant(dto);
        return new ResponseEntity<>("success!", HttpStatus.OK);
    }

    @PostMapping(path = "/getDynamicPrice")
    public ResponseEntity<String> getDynamicPrice(@RequestBody DynamicPriceDTO dto) throws JsonProcessingException{
        Product product = productService.findProductById(dto.getProductId());
        java.util.List<Price> prices = product.getPriceList();
        ObjectMapper mapper = new ObjectMapper();
        return new ResponseEntity<>(mapper.writeValueAsString(prices), HttpStatus.OK);
    }

    @PostMapping(path = "/getPriceByPositions")
    public ResponseEntity<String> getPriceFrom2Merchant(@RequestBody CheckedPriceDTO dto) throws JsonProcessingException{
        ObjectMapper mapper = new ObjectMapper();
        return new ResponseEntity<>(mapper.writeValueAsString(productService.getPriceFrom2Merchant(dto)), HttpStatus.OK);
    }

    @PostMapping(path = "/packageUpload")
    public ResponseEntity<String> upload(@RequestBody String loadJson) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        JSONPackageLoadTemplate template = mapper.readValue(loadJson, JSONPackageLoadTemplate.class);
        for (Product product:template.getList()) {
            productService.createProduct(product);
        }
        return new ResponseEntity<>("success!", HttpStatus.OK);
    }

}
