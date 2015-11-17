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
 *
 * If Grocery.price == -1, price has not been set
 */
@ParseClassName("Groceries")
public class Grocery extends ParseObject {
    private UUID mID;
    private double mPrice; // TODO: make BigDecimal, or format for currency
    private int mQuantity;

    /**
     * Default constructor
     */
    public Grocery() {
    }

    /**
     * Creates a new grocery refence
     * @param name the name of the grocery
     * @param addedBy the person that added the grocery
     * @param isFor who the grocery is for
     * @param quantity the quantity of the grocery
     */
    public Grocery(String name, String addedBy, String isFor, int quantity) {
        setName(name);
        setIsFor(isFor);
        setAddedBy(addedBy);
        setPrice(-1.0);
        setQuantity(quantity);
    }

    /**
     * Creates a new grocery reference
     * @param name the name of the grocery
     * @param addedBy the person that added the grocery
     * @param isFor who the grocery is for
     * @param quantity the quantity of the grocery
     * @param price the estimated cost of the grocery
     */
    public Grocery (String name, String addedBy, String isFor, double price, int quantity) {
        setName(name);
        setIsFor(isFor);
        setAddedBy(addedBy);
        setPrice(price);
        setQuantity(quantity);
    }

    /**
     * Creates a new grocery reference from a Parse object
     * @param object the parse object used to create the grocery
     */
    public Grocery (ParseObject object) {
        setObjectId(object.getObjectId());
        setName(object.getString("Name"));
        setIsFor(object.getString("IsFor"));
        setAddedBy(object.getString("AddedBy"));
        setPrice(object.getDouble("Price"));
        setQuantity(object.getInt("Quantity"));
    }

    /**
     * Returns the UUID of the object
     * @return UUID
     */
    public UUID getID() {
        return mID;
    }

    // setter methods

    /**
     * Sets the name of the grocery
     * @param value the name of the grocery
     */
    public void setName(String value) {
        put("Name", value);
    }

    /**
     * Sets the name of person who added the grocery
     * @param value the name of the person
     */
    public void setAddedBy(String value) {
        put("AddedBy", value);
    }

    /**
     * Sets the name of person who the grocery is for
     * @param value the name of the person
     */
    public void setIsFor(String value) {
        put("IsFor", value);
    }

    /**
     * Sets the estimated price of the grocery
     * @param price estimated price of the grocery
     */
    public void setPrice(double price) {
        put("Price", price);
    }

    /**
     * Sets the quantity of the grocery
     * @param quantity quantity of the grocery
     */
    public void setQuantity(int quantity) {
        put("Quantity", quantity);
    }

    // getter methods

    /**
     *
     * Returns the name of the grocery
     * @return the name of the grocery
     */
    public String getName() {
        return getString("Name");
    }

    /**
     * Returns the name of the person who added the grocery
     * @return the name of the person who added the grocery
     */
    public String getAddedBy() {
        return getString("AddedBy");
    }

    /**
     * Returns the name of the person who the grocery is for
     * @return the name of the person who the grocery is for
     */
    public String getIsFor() {
        return getString("IsFor");
    }

    /**
     * Returns the price the grocery
     * @return the price the grocery
     */
    public double getPrice() {
        return getInt("Price");
    }

    /**
     * Returns the quantity the grocery
     * @return the quantity the grocery
     */
    public int getQuantity() {
        return getInt("Quantity");
    }

    /**
     * Returns the objectId of the parse object
     * @return the objectId of the parse object
     */
    public String getParseId() { return getObjectId(); }

    /**
     * Creates the query for the Grocery class in parse
     * @return the query for the Grocery class in parse
     */
    public static ParseQuery<Grocery> getQuery() {
        return ParseQuery.getQuery(Grocery.class);
    }
}