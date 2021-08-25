package com.pricemonitor.dto;

public class ProductForMerchantDTO {

    private String nameMerchant;
    private int merchantID;
    private String nameProduct;
    private String categoryName;
    private int categoryID;
    private String boxing;
    private String monyName;
    private int totalSum;

    public ProductForMerchantDTO(String nameMerchant, int merchantID, String nameProduct, String categoryName,
                                 int categoryID, String boxing, String monyName, int totalSum) {
        this.nameMerchant = nameMerchant;
        this.merchantID = merchantID;
        this.nameProduct = nameProduct;
        this.categoryName = categoryName;
        this.categoryID = categoryID;
        this.boxing = boxing;
        this.monyName = monyName;
        this.totalSum = totalSum;
    }

    public ProductForMerchantDTO() {
    }

    public String getNameMerchant() {
        return nameMerchant;
    }

    public void setNameMerchant(String nameMerchant) {
        this.nameMerchant = nameMerchant;
    }

    public int getMerchantID() {
        return merchantID;
    }

    public void setMerchantID(int merchantID) {
        this.merchantID = merchantID;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public String getBoxing() {
        return boxing;
    }

    public void setBoxing(String boxing) {
        this.boxing = boxing;
    }

    public String getMonyName() {
        return monyName;
    }

    public void setMonyName(String monyName) {
        this.monyName = monyName;
    }

    public int getTotalSum() {
        return totalSum;
    }

    public void setTotalSum(int totalSum) {
        this.totalSum = totalSum;
    }
}
