package com.pricemonitor.service;

import com.pricemonitor.ContextConfigurationTest;
import com.pricemonitor.JPAConfigureTest;
import com.pricemonitor.dto.ProductDTO;
import com.pricemonitor.entity.Product;
import com.pricemonitor.repository.IProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {ContextConfigurationTest.class, JPAConfigureTest.class})
class ProductServiceTest {

    @Autowired
    private IProductRepository productRepository;

    @InjectMocks
    private ProductService productService;


    @BeforeEach
    void setUp() {
        productService = mock(ProductService.class);
    }

    @Test
    void whenCreateProduct() {
        Product product = new Product();
        product.setName("test");

        int countBeforeInsertProduct = productRepository.findAllProduct().size();

        productService.createProduct(product);

        verify(productService, times(1)).createProduct(product);

        assertEquals(countBeforeInsertProduct, productRepository.findAllProduct().size());
    }

    @Test
    void whenUpdateProduct() {
        ProductDTO dto = new ProductDTO();
        dto.setProductID(2);
        dto.setName("test");

        Product product = productRepository.findProductById(2);

        product.setName(dto.getName());

        productService.updateProduct(dto);

        verify(productService, times(1)).updateProduct(dto);
    }

    @Test
    void whenDeleteProduct() {
        Product product = productRepository.findProductById(2);

        productService.deleteProduct(product);

        verify(productService, times(1)).deleteProduct(product);
    }
}