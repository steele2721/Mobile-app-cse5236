package cse5321.roommateapp;

import android.content.Context;
import android.util.Log;

import com.parse.FindCallback;
import com.parse.ParseACL;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

/**
 * Class for interfacing with Parse
 */
public class ParseHelper {

    /**
     * Updates the Grocery List to the most current listing from parse.
     */
    public static void getGroceryList (){
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Groceries");
        Log.d(Context.class.toString(), "Starting Grocery Query.");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null) {
                    Log.d(Context.class.toString(), "Getting Grocery List.");
                    GroceryList groceryList = GroceryList.get();
                    groceryList.recreate(objects);
                } else {
                    Log.e(Context.class.toString(), e.toString());
                }
            }
        });
    }

    /**
     * Addes a new grocery to parse, and updates an old grocery in parse.
     * @param newGrocery the grocery to add or update.
     */
    public static void updateGrocery(Grocery newGrocery){
        GroceryList list = GroceryList.get();
        list.addGrocery(newGrocery);

        UserList users = UserList.get();
        List<User> userList = users.getUserList();
        ParseACL groupACL = new ParseACL();
        for (User user : userList) {
            groupACL.setReadAccess(user.getParseUser(), true);
            groupACL.setWriteAccess(user.getParseUser(), true);
        }
        newGrocery.setACL(groupACL);
        newGrocery.saveInBackground();
    }

    /**
     * Removes a grocery from parse. The grocery to be removed must be selected from the lsit of current groceries.
     * @param grocery the grocery to be removed
     */
    public static void removeGrocery (Grocery grocery){
        grocery.deleteInBackground();
    }

    /**
     * Updates the Expense List to the most current listing from parse.
     */
    public static void getExpenseList (){
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Expenses");
        Log.d(Context.class.toString(), "Starting Expense Query.");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null) {
                    Log.d(Context.class.toString(), "Getting Expense List.");
                    ExpenseList expenseList = ExpenseList.get();
                    expenseList.recreate(objects);
                } else {
                    Log.e(Context.class.toString(), e.toString());
                }
            }
        });
    }

    /**
     * Addes a new expense to parse, and updates an old expense in parse.
     * @param newExpense the grocery to add or update.
     */
    public static void updateExpense (Expense newExpense){
        ExpenseList list = ExpenseList.get();
        list.addExpense(newExpense);

        UserList users = UserList.get();
        List<User> userList = users.getUserList();
        ParseACL groupACL = new ParseACL();
        for (User user : userList) {
            groupACL.setReadAccess(user.getParseUser(), true);
            groupACL.setWriteAccess(user.getParseUser(), true);
        }
        newExpense.setACL(groupACL);
        newExpense.saveInBackground();
    }

    /**
     * Removes a expense from parse. The expense to be removed must be selected from the list of current expenses.
     * @param expense the grocery to be removed
     */
    public static void removeExpense (Expense expense){
        expense.deleteInBackground();
    }

    /**
     * Updates the the list of users from the current list on parse.
     */
    public static void getUserList (){
        ParseQuery<ParseUser> query = ParseUser.getQuery();
        Log.d(Context.class.toString(), "");
        query.findInBackground(new FindCallback<ParseUser>() {
            @Override
            public void done(List<ParseUser> objects, ParseException e) {
                if (e == null) {
                    Log.d(Context.class.toString(), "Getting User List.");
                    UserList userList = UserList.get();
                    userList.recreate(objects);
                } else {
                    Log.e(Context.class.toString(), e.toString());
                }
            }
        });
    }

    /**
     * Gets the current user and reaps it into a user object
     * @return user containing the information of the current user.
     */
    public static User getCurentUser (){
        return new User(ParseUser.getCurrentUser());
    }
}
