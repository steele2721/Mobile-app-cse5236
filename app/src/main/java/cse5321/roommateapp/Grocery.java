package cse5321.roommateapp;

import java.util.UUID;

/**
 * Created by ryan on 10/25/15.
 *
 * If Grocery.price == -1, price has not been set
 */
public class Grocery {
    private UUID mID;
    private String mName, mAddedBy, mIsFor;
    private int mPrice;

    public Grocery(String name, String addedBy, String isFor) {
        mName = name;
        mAddedBy = addedBy;
        mIsFor = isFor;
        mPrice = -1;
    }

    public Grocery (String name, String addedBy, String isFor, int price) {
        mName = name;
        mAddedBy = addedBy;
        mIsFor = isFor;
        mPrice = price;
    }

    public Grocery(String name) {
        mName = name;
        mAddedBy = null;
        mIsFor = null;
        mPrice = -1;
    }

    public UUID getID() {
        return mID;
    }

    // setter methods

    public void setPrice(int price) {
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

    public int getPrice() {
        return mPrice;
    }


}
