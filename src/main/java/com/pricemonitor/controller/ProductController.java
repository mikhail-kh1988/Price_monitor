package com.pricemonitor.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pricemonitor.dto.*;
import com.pricemonitor.entity.*;
import com.pricemonitor.service.CategoryService;
import com.pricemonitor.service.MerchantService;
import com.pricemonitor.service.ProductService;
import com.pricemonitor.tools.JSONConverter;
import com.pricemonitor.tools.JSONPackageLoadTemplate;
import com.pricemonitor.tools.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;

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
    public String getAllProduct(){
        JSONConverter converter = new JSONConverter(productService.getAllProducts());
        return converter.getJSON();
    }

    @PostMapping("/getProductByCategory")
    public String getProductByCategoryName(@RequestBody CategoryDTO dto){
        Category category = categoryService.findCategoryByName(dto.getCategoryName());
        JSONConverter converter = new JSONConverter(productService.findProductByCategory(category));
        return converter.getJSON();
    }

    @PostMapping(path = "/addProduct")
    public String createNewProduct(@RequestBody ProductDTO dto){
        Product product = new Product();
        Category category = categoryService.findCategoryById(dto.getCategoryID());

        Price price = new Price();
        price.setMoney("RUB");
        price.setTotal(dto.getPrice());

        product.setName(dto.getName());
        java.util.List<Price> priceList = new ArrayList<>();
        priceList.add(price);
        product.setPriceList(priceList);

        product.setCategory(category);
        product.setBoxing(dto.getBoxing());

        productService.createProduct(product);
        return "success!";
    }

    @PostMapping(path = "/updateProduct")
    public String updateProduct(@RequestBody ProductDTO dto){
        Product product = productService.findProductById(dto.getProductID());
        product.setName(dto.getName());
        product.setBoxing(dto.getBoxing());
        product.setCategory(categoryService.findCategoryById(dto.getCategoryID()));

        Price price = new Price();
        price.setTotal(dto.getPrice());
        price.setMoney("RUB");

        java.util.List<Price> priceList = product.getPriceList();
        priceList.add(price);
        product.setPriceList(priceList);
        productService.updateProduct(product);
        return "success!";
    }

    @PostMapping(path = "/deleteProduct")
    public String deleteProduct(@RequestBody ProductDTO dto){
        Product product = productService.findProductById(dto.getProductID());
        productService.deleteProduct(product);
        return "success";

    }

    @PostMapping(path = "/productsByMerchant")
    public String getProductByMerchant(@RequestBody MerchantDTO dto){
        Merchant merchant = merchantService.findMerchantById(dto.getID());
        java.util.List<Product> list = merchantService.getAllProductByMerchant(merchant);
        JSONConverter converter = new JSONConverter(list);
        return converter.getJSON();
    }

    @PostMapping(path = "/addProductForMerchant")
    public String addNewProduct(@RequestBody ProductForMerchantDTO dto){
        Product product = new Product();
        Price price = new Price();
        Category category = categoryService.findCategoryById(dto.getCategoryID());
        Merchant merchant = merchantService.findMerchantById(dto.getMerchantID());

        price.setMoney(dto.getMonyName());
        price.setTotal(dto.getTotalSum());

        java.util.List<Price> priceList = new ArrayList<>();
        priceList.add(price);
        product.setPriceList(priceList);
        product.setCategory(category);
        product.setName(dto.getNameProduct());
        product.setBoxing(dto.getBoxing());

        merchantService.addNewProduct(merchant, product);
        return "success!";
    }

    @PostMapping(path = "/linkedPriceProductWithMerchant")
    public String linkedProductWithMerchant(@RequestBody PriceProductMerchantDTO dto){
        Price price = new Price();
        price.setMoney(dto.getMoney());
        price.setTotal(dto.getTotalSum());

        Merchant merchant = merchantService.findMerchantById(dto.getMerchantId());
        Product product = productService.findProductById(dto.getProductId());

        product.getPriceList().add(price);

        productService.updateProduct(product);

        return "success!";
    }

    @PostMapping(path = "/getDynamicPrice")
    public String getDynamicPrice(@RequestBody DynamicPriceDTO dto){
        Product product = productService.findProductById(dto.getProductId());
        java.util.List<Price> prices = product.getPriceList();
        JSONConverter converter = new JSONConverter(prices);
        return converter.getJSON();
    }

    @PostMapping(path = "/getPriceByPositions")
    public String getPriceFrom2Merchant(@RequestBody CheckedPriceDTO dto){
        java.util.List<Template> templateList = new ArrayList<>();

        Merchant merchant1 = merchantService.findMerchantById(dto.getMerchantId1());
        Merchant merchant2 = merchantService.findMerchantById(dto.getMerchantId2());
        Product product = productService.findProductById(dto.getProductId());

        java.util.List<Product> productListMerch1 = merchant1.getProductList();
        for (Product p: productListMerch1) {
            if (p.getName().equals(product.getName())){
                Template template = new Template();
                template.setMerchantName(merchant1.getName());
                template.setProductName(p.getName());
                template.setPriceList(p.getPriceList());
                templateList.add(template);
            }
        }
        java.util.List<Product> productListMerch2 = merchant2.getProductList();
        for (Product p: productListMerch2) {
            if (p.getName().equals(product.getName())){
                Template template = new Template();
                template.setMerchantName(merchant2.getName());
                template.setProductName(p.getName());
                template.setPriceList(p.getPriceList());
                templateList.add(template);
            }
        }

        JSONConverter converter = new JSONConverter(templateList);
        return converter.getJSON();
    }

    @PostMapping(path = "/packageUpload")
    public String upload(@RequestBody String loadJson) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        JSONPackageLoadTemplate template = mapper.readValue(loadJson, JSONPackageLoadTemplate.class);
        for (Product product:template.getList()) {
            productService.createProduct(product);
        }
        return "success!";
    }

}
