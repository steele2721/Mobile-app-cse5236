package cse5321.roommateapp;

import com.parse.ParseObject;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;
import java.util.UUID;

/**
 * Created by ryan on 10/25/15.
 *
 * If Grocery.price == -1, price has not been set
 */
public class Grocery extends ParseObject {
    private UUID mID;
    private String mName, mAddedBy, mIsFor;
    private double mPrice; // TODO: make BigDecimal, or format for currency

    public Grocery(String name, String addedBy, String isFor) {
        mID = UUID.randomUUID();
        mName = name;
        mAddedBy = addedBy;
        mIsFor = isFor;
        mPrice = -1.0;
    }

    public Grocery (String name, String addedBy, String isFor, double price) {
        mID = UUID.randomUUID();
        mName = name;
        mAddedBy = addedBy;
        mIsFor = isFor;
        mPrice = price;
    }

    public Grocery(String name) {
        mID = UUID.randomUUID();
        mName = name;
        mAddedBy = null;
        mIsFor = null;
        mPrice = -1.0;
    }

    public UUID getID() {
        return mID;
    }

    // setter methods

    public void setPrice(double price) {
        mPrice = price;
    }

    // getter methods

    public String getName() {
        return mName;
    }

    public String getAddedBy() {
        return mAddedBy;
    }

    public String getIsFor() {
        return mIsFor;
    }

    public double getPrice() { return mPrice; }


}
