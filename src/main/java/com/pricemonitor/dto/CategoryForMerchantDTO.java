package com.pricemonitor.dto;

public class CategoryForMerchantDTO {
    private String categoryName;
    private String CategoryDescription;
    private int merchantID;

    public CategoryForMerchantDTO(String categoryName, String categoryDescription, int merchantID) {
        this.categoryName = categoryName;
        CategoryDescription = categoryDescription;
        this.merchantID = merchantID;
    }

    public CategoryForMerchantDTO() {
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryDescription() {
        return CategoryDescription;
    }

    public void setCategoryDescription(String categoryDescription) {
        CategoryDescription = categoryDescription;
    }

    public int getMerchantID() {
        return merchantID;
    }

    public void setMerchantID(int merchantID) {
        this.merchantID = merchantID;
    }
}
