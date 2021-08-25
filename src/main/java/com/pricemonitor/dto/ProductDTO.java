package com.pricemonitor.dto;

public class ProductDTO {

    private int productID;
    private String name;
    private int price;
    private int categoryID;
    private String boxing;

    public ProductDTO(String name, int price, int categoryID, String boxing, int productID) {
        this.name = name;
        this.price = price;
        this.categoryID = categoryID;
        this.boxing = boxing;
        this.productID = productID;
    }

    public ProductDTO() {
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
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
}
