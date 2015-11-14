package cse5321.roommateapp;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.Date;
import java.util.UUID;

/**
 * The Expense Object to be used with the application.
 * Created by ryan on 11/2/15.
 */
@ParseClassName("Expenses")
public class Expense extends ParseObject{
    private UUID mID;

    public Expense() {
    }

    public Expense(String name, String paidBy, String type, double ammount, String dueDate) {
        setName(name);
        setType(type);
        setPaidBy(paidBy);
        setAmount(ammount);
        setDueDate(dueDate);
    }

    public Expense(ParseObject object) {
        setName(object.getString("objectID"));
        setPaidBy(object.getString("PaidBy"));
        setType(object.getString("Type"));
        setAmount(object.getDouble("Amount"));
        setDueDate(object.getString("Date"));
    }

    public UUID getID() {
        return mID;
    }

    // setter methods
    public void setName(String value) {
        put("Name", value);
    }

    public void setType(String value) {
        put("Type", value);
    }

    public void setPaidBy(String value) {
        put("PaidBy", value);
    }

    public void setAmount(double price) {
        put("Amount", price);
    }

    public void setDueDate(String dueDate) {
        if (dueDate != null){
            put("Date", dueDate);
        }
    }


    // getter methods

    public String getName() {
        return getString("Name");
    }

    public String getType() {
        return getString("Type");
    }

    public String getPaidBy() {
        return getString("PaidBy");
    }

    public double getPrice() {
        return getInt("Amount");
    }

    public String getDueDate() {
        return getString("Date");
    }

    public String getParseId() { return getObjectId(); }

    public static ParseQuery<Expense> getQuery() {
        return ParseQuery.getQuery(Expense.class);
    }
}
