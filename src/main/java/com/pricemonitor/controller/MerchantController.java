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
import org.bouncycastle.asn1.cms.RsaKemParameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/merchant")
public class MerchantController {

    @Autowired
    private MerchantService merchantService;

    @GetMapping("/all")
    public ResponseEntity<String> getAllMerchant(){
        JSONConverter converter = new JSONConverter(merchantService.getAllMerchant());
        return new ResponseEntity<>(converter.getJSON(), HttpStatus.OK);
    }

    @PostMapping(path = "/addMerchant")
    public ResponseEntity<String> crateNewMerchant(@RequestBody NewMerchantDTO dto){
        merchantService.createNewMerchant(dto);
        return new ResponseEntity<>("success!", HttpStatus.OK);
    }

    @PostMapping(path = "/deleteMerchant")
    public ResponseEntity<String> deleteMerchant(@RequestBody MerchantDTO dto){
        merchantService.deleteMerchant(dto);
        return new ResponseEntity<>("success!", HttpStatus.OK);
    }

    @PostMapping(path = "/updateMerchant")
    public ResponseEntity<String> updateMerchant(@RequestBody MerchantDTO dto){
        merchantService.updateMerchant(dto);
        return new ResponseEntity<>("success!", HttpStatus.OK);
    }
}
