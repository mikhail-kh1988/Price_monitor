package com.pricemonitor.service;

import com.pricemonitor.dto.*;
import com.pricemonitor.entity.*;
import com.pricemonitor.repository.IProductRepository;
import com.pricemonitor.tools.Template;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

@Service
public class ProductService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IProductRepository productRepository;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private MerchantService merchantService;

    public void createProduct(ProductDTO dto){
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

        productRepository.createProduct(product);
        logger.info("["+this.getClass().getCanonicalName()+"] Вызван метод создания продукта "+product.getName());
    }

    public void createProduct(Product product){
        productRepository.createProduct(product);
        logger.info("["+this.getClass().getCanonicalName()+"] Вызван метод создания продукта "+product.getName());
    }

    public void createProductForMerchant(ProductForMerchantDTO dto){
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
        logger.info("["+this.getClass().getCanonicalName()+"] Вызван метод создания продукта "+product.getName()+" для магазина "+merchant.getName());
    }

    public void updateProduct(ProductDTO dto){
        Product product = findProductById(dto.getProductID());
        product.setName(dto.getName());
        product.setBoxing(dto.getBoxing());
        product.setCategory(categoryService.findCategoryById(dto.getCategoryID()));

        Price price = new Price();
        price.setTotal(dto.getPrice());
        price.setMoney("RUB");

        java.util.List<Price> priceList = product.getPriceList();
        priceList.add(price);
        product.setPriceList(priceList);
        productRepository.updateProduct(product);
        logger.info("["+this.getClass().getCanonicalName()+"] Вызван метод обновления продукта.");
    }

    public void updateProduct(Product product){
        productRepository.updateProduct(product);
        logger.info("["+this.getClass().getCanonicalName()+"] Вызван метод обновления продукта. ");
    }

    public void deleteProduct(Product product){
        productRepository.removeProduct(product);
        logger.info("["+this.getClass().getCanonicalName()+"] Вызван метод удаления продукта. ");
    }

    public Product findProductById(int id){
        logger.info("["+this.getClass().getCanonicalName()+"] Вызван метод поиска продукта по ID = "+id);
        return productRepository.findProductById(id);
    }

    public java.util.List<Product> getAllProducts(){
        logger.info("["+this.getClass().getCanonicalName()+"] Вызван метод вывода списка всех продуктов.");
        return productRepository.findAllProduct();
    }

    public java.util.List<Product> findProductByCategory(CategoryDTO dto){
        Category category = categoryService.findCategoryByName(dto.getCategoryName());
        logger.info("["+this.getClass().getCanonicalName()+"] Вызван метод поиска списка всех продуктов по определенной категории "+category.getName());
        return productRepository.findProductByCategory(category);
    }

    public java.util.List<Product> findProductByBoxing(String boxing){
        return productRepository.findProductByBoxing(boxing);
    }

    public void linkedProductWithMerchant(PriceProductMerchantDTO dto){
        Price price = new Price();
        price.setMoney(dto.getMoney());
        price.setTotal(dto.getTotalSum());

        Merchant merchant = merchantService.findMerchantById(dto.getMerchantId());
        Product product = findProductById(dto.getProductId());

        product.getPriceList().add(price);

        updateProduct(product);
        logger.info("["+this.getClass().getCanonicalName()+"] Вызван метод связывания проукта к конкретному магазину. ");
    }

    public java.util.List<Template> getPriceFrom2Merchant(CheckedPriceDTO dto){
        java.util.List<Template> templateList = new ArrayList<>();

        Merchant merchant1 = merchantService.findMerchantById(dto.getMerchantId1());
        Merchant merchant2 = merchantService.findMerchantById(dto.getMerchantId2());
        Product product = findProductById(dto.getProductId());

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

        logger.info("["+this.getClass().getCanonicalName()+"] Вызван метод получения цены по двум магазмнам");
        return templateList;
    }

}
