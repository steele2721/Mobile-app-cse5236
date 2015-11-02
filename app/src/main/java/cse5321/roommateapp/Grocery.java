package cse5321.roommateapp;

import java.util.UUID;

/**
 * Created by ryan on 10/25/15.
 */
public class Grocery {
    private UUID mID;
    private String mName, mAddedBy, mIsFor;
    private int price;

    public Grocery(String name, String addedBy, String isFor) {
        mName = name;
        mAddedBy = addedBy;
        mIsFor = isFor;
    }

    public UUID getID() {
        return mID;
    }

    // setter methods

    public void setPrice(int value) {
        price = value;
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
        return price;
    }


}
