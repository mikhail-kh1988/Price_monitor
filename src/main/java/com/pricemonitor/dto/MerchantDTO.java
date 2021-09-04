package com.pricemonitor.dto;

public class MerchantDTO {

    private int ID;
    private String name;
    private String newName;
    private String newAddress;

    public MerchantDTO(int ID, String name) {
        this.ID = ID;
        this.name = name;
    }

    public MerchantDTO() {
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNewName() {
        return newName;
    }

    public void setNewName(String newName) {
        this.newName = newName;
    }

    public String getNewAddress() {
        return newAddress;
    }

    public void setNewAddress(String newAddress) {
        this.newAddress = newAddress;
    }
}
