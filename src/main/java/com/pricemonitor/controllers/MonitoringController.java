package com.pricemonitor.controllers;

import com.pricemonitor.dto.MerchantDTO;
import com.pricemonitor.dto.Rating2MerchantDTO;
import com.pricemonitor.entity.Merchant;
import com.pricemonitor.service.DynamicPriceService;
import com.pricemonitor.service.MerchantService;
import com.pricemonitor.service.RatingPriceByMerchantService;
import com.pricemonitor.tools.JSONConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/monitoring")
public class MonitoringController {

    @Autowired
    private DynamicPriceService dynamicPriceService;

    @Autowired
    private RatingPriceByMerchantService ratingPriceByMerchantService;

    @Autowired
    private MerchantService merchantService;

    @GetMapping("/allDynamicPrice")
    public String getAllDynamicPrice(){
        JSONConverter converter = new JSONConverter(dynamicPriceService.getAllDynamicPrice());
        return converter.getJSON();
    }

    @PostMapping(path = "/getRating2Merchant")
    public String getRatingPriceBy2Merchant(@RequestBody Rating2MerchantDTO dto){
        Merchant merchant1 = merchantService.findMerchantById(dto.getMerchantID1());
        Merchant merchant2 = merchantService.findMerchantById(dto.getMerchantID2());
        JSONConverter converter = new JSONConverter(ratingPriceByMerchantService.getListRatingBetweenMerchant(merchant1, merchant2));
        return converter.getJSON();
    }

}
