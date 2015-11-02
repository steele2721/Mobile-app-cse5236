package cse5321.roommateapp;

import java.util.UUID;

/**
 * Created by ryan on 11/2/15.
 */
public class Expense {
    private UUID mId;
    private String mName, mType;
    private double mAmount;

    public Expense(String name, String type, double amount) {
        mName = name;
        mType = type;
        mAmount = amount;
    }

    public UUID getID() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public String getType() {
        return mType;
    }

    public double getAmount() {
        return mAmount;
    }


}
