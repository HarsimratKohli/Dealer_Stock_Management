package com.example.android.stockmanagement;

import java.io.Serializable;

/**
 * Created by Harsimrat Kohli on 30-04-2018.
 */

public class Product implements Serializable{

    public static final String TAG = "Product";
    private static final long serialVersionUID = -7406082437623008161L;

    private long mId;
    private String mType;
    private String mQuantity;
    private String mPrice;
    private String mCommision;
    private Dealer mDealer;

    public Product() {

    }

    public Product(String Type, String Quantity, String Price, String Commision) {

        this.mType=Type;
        this.mQuantity = Quantity;
        this.mPrice = Price;
        this.mCommision = Commision;
    }


    public long getId()
    {
        return mId;
    }

    public void setId(long mId) {
        this.mId = mId;
    }

    public String getmType() {
        return mType;
    }

    public void setmType(String mType) {
        this.mType = mType;
    }

    public String getmQuantity() {
        return mQuantity;
    }

    public void setmQuantity(String mQuantity) {
        this.mQuantity = mQuantity;
    }

    public String getmCommision() {
        return mCommision;
    }

    public void setmCommision(String mCommision) {
        this.mCommision = mCommision;
    }

    public String getmPrice() {
        return mPrice;
    }

    public void setmPrice(String mPrice) {
        this.mPrice = mPrice;
    }

    public String getPrice() {
        return mPrice;
    }

    public void setPrice(String mPrice) {
        this.mPrice = mPrice;
    }

    public Dealer getDealer() {
        return mDealer;
    }

    public void setDealer(Dealer  mDealer) {
        this.mDealer = mDealer;
    }
}
