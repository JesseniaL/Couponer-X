package applications.listadapter;

/**
 * Created by Henry on 10/26/2017.
 */

public class Information {

    private String storeName;
    private String expirationDate;
    private String couponNumber;

    Information(String storeName, String expirationDate, String couponNumber){
        this.storeName = storeName;
        this.expirationDate = expirationDate;
        this.couponNumber = couponNumber;
    }

    public String getStoreName(){ return storeName; }
    public String getExpirationDate(){ return expirationDate; }
    public String getCouponNumber(){ return couponNumber; }
}
