package cse5321.roommateapp;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;
import java.util.UUID;

/**
 * The Grocery Object to be used with the application.
 * Created by ryan on 10/25/15.
 *
 * If Grocery.price == -1, price has not been set
 */
@ParseClassName("Grocery")
public class Grocery extends ParseObject {
    private UUID mID;
    private double mPrice; // TODO: make BigDecimal, or format for currency

    public Grocery(String name, String addedBy, String isFor) {
        setName(name);
        setIsFor(isFor);
        setAddedBy(addedBy);
        setPrice(-1.0);
    }

    public Grocery (String name, String addedBy, String isFor, double price) {
        setName(name);
        setIsFor(isFor);
        setAddedBy(addedBy);
        setPrice(price);
    }

    public UUID getID() {
        return mID;
    }

    // setter methods
    public void setName(String value) {
        setObjectId(value);
    }

    public void setAddedBy(String value) {
        put("AddedBy", value);
    }

    public void setIsFor(String value) {
        put("IsFor", value);
    }

    public void setPrice(double price) {
        put("Price", price);
    }

    // getter methods

    public String getName() {
        return getObjectId();
    }

    public String getAddedBy() {
        return getString("AddedBy");
    }

    public String getIsFor() {
        return getString("IsFor");
    }

    public double getPrice() {
        return getInt("Price");
    }

    public static ParseQuery<Grocery> getQuery() {
        return ParseQuery.getQuery(Grocery.class);
    }


}
