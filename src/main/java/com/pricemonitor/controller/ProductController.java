package com.pricemonitor.controller;

import com.pricemonitor.dto.CategoryDTO;
import com.pricemonitor.dto.MerchantDTO;
import com.pricemonitor.dto.ProductDTO;
import com.pricemonitor.dto.ProductForMerchantDTO;
import com.pricemonitor.entity.Category;
import com.pricemonitor.entity.Merchant;
import com.pricemonitor.entity.Price;
import com.pricemonitor.entity.Product;
import com.pricemonitor.service.CategoryService;
import com.pricemonitor.service.MerchantService;
import com.pricemonitor.service.ProductService;
import com.pricemonitor.tools.JSONConverter;
import com.pricemonitor.tools.XLSXFileReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.*;
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

/*    @PostMapping(path = "/upload", headers = "content-type=multipart/*")
    public String uploadXSLXFile(@RequestParam MultipartFile file ) throws IOException {
        String UPLOAD_DIR = "/home/mikhail/DEV/1/";*/
    @GetMapping("/upload")
    public String upload() throws IOException {

        //file.transferTo(new File(UPLOAD_DIR+file.getOriginalFilename()));


        FileInputStream fileInputStream = new FileInputStream("/home/mikhail/DEV/loadBook.xlsx");
        InputStream inputStream = new BufferedInputStream(fileInputStream);
        XLSXFileReader reader = new XLSXFileReader(inputStream);
        JSONConverter converter = new JSONConverter(reader.getProductListFromFile());
        return converter.getJSON();
    }



}
