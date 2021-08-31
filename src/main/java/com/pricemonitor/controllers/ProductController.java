package com.pricemonitor.controllers;

import com.pricemonitor.dto.CategoryDTO;
import com.pricemonitor.dto.ProductDTO;
import com.pricemonitor.entity.Category;
import com.pricemonitor.entity.Price;
import com.pricemonitor.entity.Product;
import com.pricemonitor.service.CategoryService;
import com.pricemonitor.service.ProductService;
import com.pricemonitor.tools.JSONConverter;
import com.pricemonitor.tools.XLSXFileReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.*;

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
        product.setPrice(price);
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

        product.setPrice(price);
        productService.updateProduct(product);
        return "success!";
    }

    @PostMapping(path = "/deleteProduct")
    public String deleteProduct(@RequestBody ProductDTO dto){
        Product product = productService.findProductById(dto.getProductID());
        productService.deleteProduct(product);
        return "success";

    }

    /*@PostMapping(path = "/upload")
    public String uploadXSLXFile(//@RequestParam("name") String name,
                                 @RequestParam("file")MultipartFile file) throws IOException {
        String UPLOAD_DIR = "/home/mikhail/DEV/1/";*/
    @GetMapping("/upload")
    public String upload() throws IOException {
/*
        byte[] bytes = file.getBytes();
        Path path = Paths.get(UPLOAD_DIR + file.getOriginalFilename());
        Files.write(path, bytes);*/

        FileInputStream fileInputStream = new FileInputStream("/home/mikhail/DEV/loadBook.xlsx");
        InputStream inputStream = new BufferedInputStream(fileInputStream);
        XLSXFileReader reader = new XLSXFileReader(inputStream);
        JSONConverter converter = new JSONConverter(reader.getProductListFromFile());
        return converter.getJSON();
    }



}
