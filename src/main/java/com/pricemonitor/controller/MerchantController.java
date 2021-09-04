package com.pricemonitor.controller;

import com.pricemonitor.dto.*;
import com.pricemonitor.entity.Category;
import com.pricemonitor.entity.Merchant;
import com.pricemonitor.entity.Price;
import com.pricemonitor.entity.Product;
import com.pricemonitor.service.CategoryService;
import com.pricemonitor.service.MerchantService;
import com.pricemonitor.service.ProductService;
import com.pricemonitor.tools.JSONConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/merchant")
public class MerchantController {

    @Autowired
    private MerchantService merchantService;

    @GetMapping("/all")
    public String getAllMerchant(){
        JSONConverter converter = new JSONConverter(merchantService.getAllMerchant());
        return converter.getJSON();
    }

    @PostMapping(path = "/addMerchant")
    public String crateNewMerchant(@RequestBody NewMerchantDTO dto){
        Merchant merchant = new Merchant();
        merchant.setName(dto.getMerchantName());
        merchant.setAddress(dto.getMerchantAddress());
        merchantService.createNewMerchant(merchant);
        return "success!";
    }

    @PostMapping(path = "/deleteMerchant")
    public String deleteMerchant(@RequestBody MerchantDTO dto){
        Merchant merchant = merchantService.findMerchantById(dto.getID());
        merchantService.deleteMerchant(merchant);
        return "success!";
    }

    @PostMapping(path = "/updateMerchant")
    public String updateMerchant(@RequestBody MerchantDTO dto){
        Merchant merchant = merchantService.findMerchantById(dto.getID());
        merchant.setName(dto.getNewName());
        merchant.setAddress(dto.getNewAddress());
        merchantService.updateMerchant(merchant);
        return "success!";
    }
}
