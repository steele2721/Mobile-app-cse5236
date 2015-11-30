package cse5321.roommateapp;


import com.parse.ParseObject;


/**
 * The Expense Object to be used with the application.
 */
public class Expense {
    private String mID;
    private String mName;
    private String mPaidBy;
    private String mType;
    private double mPrice;
    private String mDueDate;

    /**
     * Default Constructor
     */
    public Expense() {
    }

    /**
     * Constructs a new expense reference.
     * @param name the name of the expense
     * @param paidBy the person that paid the expense
     * @param type the type of expense that it is
     * @param amount the amount of the expense
     * @param dueDate when the bill is due / paid
     */
    public Expense(String name, String paidBy, String type, double amount, String dueDate) {
        mName = name;
        mDueDate = dueDate;
        mPaidBy = paidBy;
        mType = type;
        mPrice = amount;
    }

    /**
     * Constructs a new expense reference.
     * @param object theparse object used to create the expense.
     */
    public Expense(ParseObject object) {
        mID = object.getObjectId();
        mName = object.getString("Name");
        mPaidBy = object.getString("PaidBy");
        mType = object.getString("Type");
        mPrice = object.getDouble("Price");
        mDueDate = object.getString("DueDate");
    }

    // setter methods

    /**
     * Sets the name of the expense
     * @param value the name to be set
     */
    public void setName(String value) {
        mName = value;
    }

    /**
     * Sets the type of the expense
     * @param value the type to be set
     */
    public void setType(String value) {
        mType = value;
    }

    /**
     * Sets who paid the expense
     * @param value the person who paid
     */
    public void setPaidBy(String value) {
        mPaidBy = value;
    }

    /**
     * Sets the price of the expense
     * @param price the price to be set
     */
    public void setAmount(double price) {
        mPrice =  price;
    }

    /**
     * Sets the due date of the expense
     * @param dueDate the due date to be set
     */
    public void setDueDate(String dueDate) {
            mDueDate = dueDate;
    }


    // getter methods

    /**
     * Returns the name of the expense
     * @return the name of the expense
     */
    public String getName() {
        return mName;
    }

    /**
     * Returns the type of the expense
     * @return the tpe of the expense
     */
    public String getType() {
        return mType;
    }

    /**
     * Returns the name of the person who paid the expense
     * @return the name of the person who paid the expense
     */
    public String getPaidBy() {
        return mPaidBy;
    }

    /**
     * Returns the price of the expense
     * @return the price of the expense
     */
    public double getPrice() {
        return mPrice;
    }

    /**
     * Returns the due date of the expense
     * @return the due date of the expense
     */
    public String getDueDate() {
        return mDueDate;
    }

    /**
     * Returns the UUID of the object
     * @return UUID
     */
    public String getID() {
        return mID;
    }
}
