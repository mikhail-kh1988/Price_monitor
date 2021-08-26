package com.pricemonitor.controllers;

import com.pricemonitor.service.DynamicPriceService;
import com.pricemonitor.service.RatingPriceByMerchantService;
import com.pricemonitor.tools.JSONConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/monitoring")
public class MonitoringController {

    @Autowired
    private DynamicPriceService dynamicPriceService;

    @Autowired
    private RatingPriceByMerchantService ratingPriceByMerchantService;

    @GetMapping("/allDynamicPrice")
    public String getAllDynamicPrice(){
        JSONConverter converter = new JSONConverter(dynamicPriceService.getAllDynamicPrice());
        return converter.getJSON();
    }


}
