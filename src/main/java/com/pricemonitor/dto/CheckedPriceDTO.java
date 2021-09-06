package com.pricemonitor.dto;

public class CheckedPriceDTO {

    private int productId;
    private int merchantId1;
    private int merchantId2;

    public CheckedPriceDTO(int productId, int merchantId1, int merchantId2) {
        this.productId = productId;
        this.merchantId1 = merchantId1;
        this.merchantId2 = merchantId2;
    }

    public CheckedPriceDTO() {
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getMerchantId1() {
        return merchantId1;
    }

    public void setMerchantId1(int merchantId1) {
        this.merchantId1 = merchantId1;
    }

    public int getMerchantId2() {
        return merchantId2;
    }

    public void setMerchantId2(int merchantId2) {
        this.merchantId2 = merchantId2;
    }
}
