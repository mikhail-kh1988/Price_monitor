package com.pricemonitor.dto;

public class DynamicPriceDTO {

    private int productId;
    private int merchantId;

    public DynamicPriceDTO(int productId, int merchantId) {
        this.productId = productId;
        this.merchantId = merchantId;
    }

    public DynamicPriceDTO() {
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(int merchantId) {
        this.merchantId = merchantId;
    }
}
