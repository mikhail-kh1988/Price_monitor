package com.pricemonitor.repository;

import com.pricemonitor.entity.Category;
import com.pricemonitor.entity.Product;
import com.pricemonitor.hibernate.CRUDRepository;

public interface IProductRepository extends CRUDRepository {

    void createProduct(Product product);
    Product findProductById(int id);
    java.util.List<Product> findProductByCategory(Category category);
    java.util.List<Product> findProductByBoxing(String boxing);
    java.util.List<Product> findAllProduct();
    void removeProduct(Product product);
    void updateProduct(Product product);
}
