package com.pricemonitor.dto;

import java.util.Calendar;

public class ProfileDTO {
    private int profileId;
    private String fullName;
    private String address;
    private String phone;

    public ProfileDTO(int profileId, String fullName, String address, String phone) {
        this.profileId = profileId;
        this.fullName = fullName;
        this.address = address;
        this.phone = phone;
    }

    public int getProfileId() {
        return profileId;
    }

    public void setProfileId(int profileId) {
        this.profileId = profileId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public ProfileDTO() {

    }
}
