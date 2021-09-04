package com.pricemonitor.dto;

public class CategoryLinkedDTO {

    private int merchantId;
    private int categoryId;

    public CategoryLinkedDTO() {
    }

    public CategoryLinkedDTO(int merchantId, int categoryId) {
        this.merchantId = merchantId;
        this.categoryId = categoryId;
    }

    public int getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(int merchantId) {
        this.merchantId = merchantId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
}
