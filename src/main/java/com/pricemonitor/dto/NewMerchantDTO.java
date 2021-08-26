package com.pricemonitor.dto;

public class NewMerchantDTO {
    private String merchantName;
    private String merchantAddress;

    public NewMerchantDTO(String merchantName, String merchantAddress) {
        this.merchantName = merchantName;
        this.merchantAddress = merchantAddress;
    }

    public NewMerchantDTO() {
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getMerchantAddress() {
        return merchantAddress;
    }

    public void setMerchantAddress(String merchantAddress) {
        this.merchantAddress = merchantAddress;
    }
}
