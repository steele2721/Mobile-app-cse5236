package cse5321.roommateapp;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.Date;
import java.util.UUID;

/**
 * The Expense Object to be used with the application.
 */
@ParseClassName("Expenses")
public class Expense extends ParseObject{
    private UUID mID;

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
     * @param ammount the amount of the expense
     * @param dueDate when the bill is due / paid
     */
    public Expense(String name, String paidBy, String type, double ammount, String dueDate) {
        setName(name);
        setType(type);
        setPaidBy(paidBy);
        setAmount(ammount);
        setDueDate(dueDate);
    }

    /**
     * Constructs a new expense reference.
     * @param object theparse object used to create the expense.
     */
    public Expense(ParseObject object) {
        setObjectId(object.getObjectId());
        setName(object.getString("Name"));
        setPaidBy(object.getString("PaidBy"));
        setType(object.getString("Type"));
        setAmount(object.getDouble("Amount"));
        setDueDate(object.getString("Date"));
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
     * Sets the name of the expense
     * @param value the name to be set
     */
    public void setName(String value) {
        put("Name", value);
    }

    /**
     * Sets the type of the expense
     * @param value the type to be set
     */
    public void setType(String value) {
        put("Type", value);
    }

    /**
     * Sets who paid the expense
     * @param value the person who paid
     */
    public void setPaidBy(String value) {
        put("PaidBy", value);
    }

    /**
     * Sets the price of the expense
     * @param price the price to be set
     */
    public void setAmount(double price) {
        put("Amount", price);
    }

    /**
     * Sets the due date of the expense
     * @param dueDate the due date to be set
     */
    public void setDueDate(String dueDate) {
            put("Date", dueDate);
    }


    // getter methods

    /**
     * Returns the name of the expense
     * @return the name of the expense
     */
    public String getName() {
        return getString("Name");
    }

    /**
     * Returns the type of the expense
     * @return the tpe of the expense
     */
    public String getType() {
        return getString("Type");
    }

    /**
     * Returns the name of the person who paid the expense
     * @return the name of the person who paid the expense
     */
    public String getPaidBy() {
        return getString("PaidBy");
    }

    /**
     * Returns the price of the expense
     * @return the price of the expense
     */
    public double getPrice() {
        return getInt("Amount");
    }

    /**
     * Returns the due date of the expense
     * @return the due date of the expense
     */
    public String getDueDate() {
        return getString("Date");
    }

    /**
     * Returns the objectId of the Parse object
     * @return the objectId of the Parse object
     */
    public String getParseId() { return getObjectId(); }

    /**
     * Creates the query for class
     * @return the query for class
     */
    public static ParseQuery<Expense> getQuery() {
        return ParseQuery.getQuery(Expense.class);
    }
}
