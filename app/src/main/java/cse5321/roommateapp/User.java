package cse5321.roommateapp;

import com.parse.ParseClassName;
import com.parse.ParseObject;

import java.util.UUID;

/**
 *  The User Object to be used with the application.
 * Created by John on 11/6/2015.
 */
@ParseClassName("User")
public class User extends ParseObject{
    private UUID mID;

    public User(String name) {
        setName(name);
    }

    public UUID getID() {
        return mID;
    }

    // setter methods
    public void setName(String value) {
        put("Name", value);

    }

    public void setAmountPaid(double value) {
        put("AmountPaid", value);

    }

    public void setAmountOwed(double value) {
        put("AmountOwed", value);
    }

    // getter methods
    public String getName() {
        return getString("Name");

    }

    public double getAmountPaid() {
        return getInt("AmountPaid");

    }

    public double getAmountOwed() {
        return getInt("AmountOwed");
    }

}
