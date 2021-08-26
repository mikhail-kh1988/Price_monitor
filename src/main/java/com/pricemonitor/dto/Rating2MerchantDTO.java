package com.pricemonitor.dto;

public class Rating2MerchantDTO {

    private int merchantID1;
    private int merchantID2;

    public Rating2MerchantDTO(int merchantID1, int merchantID2) {
        this.merchantID1 = merchantID1;
        this.merchantID2 = merchantID2;
    }

    public Rating2MerchantDTO() {
    }

    public int getMerchantID1() {
        return merchantID1;
    }

    public void setMerchantID1(int merchantID1) {
        this.merchantID1 = merchantID1;
    }

    public int getMerchantID2() {
        return merchantID2;
    }

    public void setMerchantID2(int merchantID2) {
        this.merchantID2 = merchantID2;
    }
}
