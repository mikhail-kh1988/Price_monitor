package com.pricemonitor.service;

import com.pricemonitor.entity.DynamicPrice;
import com.pricemonitor.entity.Price;
import com.pricemonitor.entity.Product;
import com.pricemonitor.repositories.IDynamicPriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;

@Service
public class DynamicPriceService {

    @Autowired
    private IDynamicPriceRepository dynamicPriceRepository;

    public java.util.List<DynamicPrice> getAllDynamicPrice(){
        return dynamicPriceRepository.findAllDynamicPriceList();
    }

    public DynamicPrice findDynamicPriceById(int id){
        return dynamicPriceRepository.findDynamicPriceById(id);
    }

    public void createDynamicPrice(Product product){
        DynamicPrice dynamicPrice = new DynamicPrice();
        dynamicPrice.setProduct(product);
        Price price = product.getPrice();
        dynamicPrice.setPrice(price);
        dynamicPrice.setCreateDate(Calendar.getInstance());
        dynamicPriceRepository.createDynamicPrice(dynamicPrice);
    }

    public void deleteDynamicPriceById(int id){
        dynamicPriceRepository.deleteDynamicPriceByProductId(id);
    }
}
