package com.pricemonitor.service;

import com.pricemonitor.entity.DynamicPrice;
import com.pricemonitor.repositories.IDynamicPriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DynamicPriceService {

    @Autowired
    private IDynamicPriceRepository dynamicPriceRepository;

    public java.util.List<DynamicPrice> getAllDynamicPrice(){
        return dynamicPriceRepository.findAllDynamicPriceList();
    }

    public java.util.List<DynamicPrice> get(){
        dynamicPriceRepository.
    }
}
