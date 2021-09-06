package com.pricemonitor.dto;

public class PriceProductMerchantDTO {

    private int totalSum;
    private String money;
    private int merchantId;
    private int productId;

    public PriceProductMerchantDTO(int totalSum, String money, int merchantId, int productId) {
        this.totalSum = totalSum;
        this.money = money;
        this.merchantId = merchantId;
        this.productId = productId;
    }

    public PriceProductMerchantDTO() {
    }

    public int getTotalSum() {
        return totalSum;
    }

    public void setTotalSum(int totalSum) {
        this.totalSum = totalSum;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public int getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(int merchantId) {
        this.merchantId = merchantId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
}
