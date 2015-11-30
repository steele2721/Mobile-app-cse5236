package cse5321.roommateapp;

import com.parse.ParseObject;


/**
 * The Grocery Object to be used with the application.
 *
 * If Grocery.price == -1, price has not been set
 */
public class Grocery {
    private String mID;
    private double mPrice;
    private int mQuantity;
    private String mName;
    private String mAddedBy;
    private String mIsFor;


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
        mName = name;
        mAddedBy = addedBy;
        mIsFor = isFor;
        mQuantity = quantity;
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
        mName = name;
        mAddedBy = addedBy;
        mIsFor = isFor;
        mQuantity = quantity;
        mPrice = price;
    }

    /**
     * Creates a new grocery reference from a Parse object
     * @param object the parse object used to create the grocery
     */
    public Grocery (ParseObject object) {
        mID = object.getObjectId();
        mName = object.getString("Name");
        mIsFor = object.getString("IsFor");
        mAddedBy = object.getString("AddedBy");
        mPrice = object.getDouble("Price");
        mQuantity = object.getInt("Quantity");
    }

    // setter methods

    /**
     * Sets the name of the grocery
     * @param value the name of the grocery
     */
    public void setName(String value) {
        mName = value;
    }

    /**
     * Sets the name of person who added the grocery
     * @param value the name of the person
     */
    public void setAddedBy(String value){
        mAddedBy = value;
    }

    /**
     * Sets the name of person who the grocery is for
     * @param value the name of the person
     */
    public void setIsFor(String value) {
        mIsFor = value;
    }

    /**
     * Sets the estimated price of the grocery
     * @param price estimated price of the grocery
     */
    public void setPrice(double price) {
        mPrice = price;
    }

    /**
     * Sets the quantity of the grocery
     * @param quantity quantity of the grocery
     */
    public void setQuantity(int quantity) {
        mQuantity =  quantity;
    }

    // getter methods

    /**
     *
     * Returns the name of the grocery
     * @return the name of the grocery
     */
    public String getName() {
        return mName;
    }

    /**
     * Returns the name of the person who added the grocery
     * @return the name of the person who added the grocery
     */
    public String getAddedBy() {
        return mAddedBy;
    }

    /**
     * Returns the name of the person who the grocery is for
     * @return the name of the person who the grocery is for
     */
    public String getIsFor() {
        return mIsFor;
    }

    /**
     * Returns the price the grocery
     * @return the price the grocery
     */
    public double getPrice() {
        return mPrice;
    }

    /**
     * Returns the quantity the grocery
     * @return the quantity the grocery
     */
    public int getQuantity() {
        return mQuantity;
    }

    /**
     * Returns the Id of the object
     * @return String Id
     */
    public String getID() {
        return mID;
    }
}