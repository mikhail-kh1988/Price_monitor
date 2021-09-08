package com.pricemonitor.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pricemonitor.dto.*;
import com.pricemonitor.service.MerchantService;
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
    public ResponseEntity<String> getAllMerchant() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return new ResponseEntity<>(mapper.writeValueAsString(merchantService.getAllMerchant()), HttpStatus.OK);
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
