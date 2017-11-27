package com.app.jessenialopez.couponer;

/**
 * Created by Henry on 10/26/2017.
 */

public class Information {

    private String storeName;
    private String productName;
    private String expirationDate;
    private String couponNumber;

    Information(String storeName, String productName, String expirationDate, String couponNumber){
        this.storeName = storeName;
        this.productName = productName;
        this.expirationDate = expirationDate;
        this.couponNumber = couponNumber;
    }

    public String getStoreName(){ return storeName; }
    public String getProductName(){ return productName; }
    public String getExpirationDate(){ return expirationDate; }
    public String getCouponNumber(){ return couponNumber; }
}
