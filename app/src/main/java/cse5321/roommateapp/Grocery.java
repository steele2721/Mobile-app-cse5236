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
    private int mQuantity;

    public Grocery(String name, String addedBy, String isFor, int quantity) {
        setName(name);
        setIsFor(isFor);
        setAddedBy(addedBy);
        setPrice(-1.0);
        setQuantity(quantity);
    }

    public Grocery (String name, String addedBy, String isFor, double price, int quantity) {
        setName(name);
        setIsFor(isFor);
        setAddedBy(addedBy);
        setPrice(price);
        setQuantity(quantity);
    }

    public UUID getID() {
        return mID;
    }

    // setter methods
    public void setName(String value) {
        put("Name", value);
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

    public void setQuantity(int quantity) {
        mQuantity = quantity;
    }

    // getter methods

    public String getName() {
        return getString("Name");
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

    public int getQuantity() {
        return mQuantity;
    }

    public static ParseQuery<Grocery> getQuery() {
        return ParseQuery.getQuery(Grocery.class);
    }


}
