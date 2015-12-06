package cse5321.roommateapp;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
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
     * Gets the current Grocery List from parse.
     */
    public static void getGroceryList (Context context){
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()){
            Toast.makeText(context, "No internet connection, try again later.", Toast.LENGTH_SHORT).show();
        } else {
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
    }

    /**
     * Addes a new grocery to parse, and updates an old grocery in parse.
     * @param newGrocery the grocery to add or update.
     */
    public static void addGrocery(Grocery newGrocery){
        GroceryList list = GroceryList.get();
        list.addGrocery(newGrocery);
        ParseObject grocery = new ParseObject("Groceries");
        grocery.put("Name", newGrocery.getName());
        grocery.put("AddedBy", newGrocery.getAddedBy());
        grocery.put("IsFor", newGrocery.getIsFor());
        grocery.put("Price", newGrocery.getPrice());
        grocery.put("Quantity", newGrocery.getQuantity());

        UserList users = UserList.get();
        List<User> userList = users.getUserList();
        ParseACL groupACL = new ParseACL();
        for (User user : userList) {
            groupACL.setReadAccess(user.getParseUser(), true);
            groupACL.setWriteAccess(user.getParseUser(), true);
        }
        grocery.setACL(groupACL);
        grocery.saveInBackground();
    }

    /**
     * Updates and existing grocery in parse
     * @param grocery the grocery to be updated, with the new information.
     */
    public static void updateGrocery(final Grocery grocery, Context context){
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()){
            Toast.makeText(context, "No internet connection, try again later.", Toast.LENGTH_SHORT).show();
        } else {
            ParseQuery<ParseObject> query = ParseQuery.getQuery("Groceries");
            query.getInBackground(grocery.getID(), new GetCallback<ParseObject>() {
                public void done(ParseObject object, ParseException e) {
                    if (e == null) {
                        object.put("Name", grocery.getName());
                        object.put("AddedBy", grocery.getAddedBy());
                        object.put("IsFor", grocery.getIsFor());
                        object.put("Price", grocery.getPrice());
                        object.put("Quantity", grocery.getQuantity());
                        Log.d(Context.class.toString(), "Grocery updated.");
                        object.saveInBackground();
                    } else {
                        Log.d(Context.class.toString(), "Grocery not found!");
                    }
                }
            });
        }
    }

    /**
     * Removes a grocery from parse. The grocery to be removed must be selected from the lsit of current groceries.
     * @param grocery the grocery to be removed
     */
    public static void removeGrocery (Grocery grocery){
        GroceryList list = GroceryList.get();
        list.removeGrocery(grocery);
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Groceries");
        query.getInBackground(grocery.getID(), new GetCallback<ParseObject>() {
            public void done(ParseObject object, ParseException e) {
                if (e == null) {
                    object.deleteInBackground();
                    Log.d(Context.class.toString(), "Grocery deleted");
                } else {
                    Log.d(Context.class.toString(), "Grocery not found!");
                }
            }
        });
    }

    /**
     * Updates the Expense List to the most current listing from parse.
     */
    public static void getExpenseList (Context context){ConnectivityManager connectivityManager
            = (ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()){
            Toast.makeText(context, "No internet connection, try again later.", Toast.LENGTH_SHORT).show();
        } else {

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
    }

    /**
     * Addes a new expense to parse, and updates an old expense in parse.
     * @param newExpense the grocery to add or update.
     */
    public static void addExpense(Expense newExpense){
        ExpenseList list = ExpenseList.get();
        list.addExpense(newExpense);

        ParseObject expense = new ParseObject("Expenses");
        expense.put("Name", newExpense.getName());
        expense.put("Price", newExpense.getPrice());
        expense.put("DueDate", newExpense.getDueDate());
        expense.put("Type", newExpense.getType());
        expense.put("PaidBy", newExpense.getPaidBy());

        UserList users = UserList.get();
        List<User> userList = users.getUserList();
        ParseACL groupACL = new ParseACL();
        for (User user : userList) {
            groupACL.setReadAccess(user.getParseUser(), true);
            groupACL.setWriteAccess(user.getParseUser(), true);
        }
        expense.setACL(groupACL);
        expense.saveInBackground();
    }

    public static void updateExpense(final Expense newExpense, Context context){
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()){
            Toast.makeText(context, "No internet connection, try again later.", Toast.LENGTH_SHORT).show();
        } else {
            ParseQuery<ParseObject> query = ParseQuery.getQuery("Expenses");
            query.getInBackground(newExpense.getID(), new GetCallback<ParseObject>() {
                public void done(ParseObject object, ParseException e) {
                    if (e == null) {
                        object.put("Name", newExpense.getName());
                        object.put("Price", newExpense.getPrice());
                        object.put("DueDate", newExpense.getDueDate());
                        object.put("Type", newExpense.getType());
                        object.put("PaidBy", newExpense.getPaidBy());
                        Log.d(Context.class.toString(), "Expense updated.");
                        object.saveInBackground();
                    } else {
                        Log.d(Context.class.toString(), "Expense not found!");
                    }
                }
            });
        }
    }

    /**
     * Removes a expense from parse. The expense to be removed must be selected from the list of current expenses.
     * @param expense the grocery to be removed
     */
    public static void removeExpense (Expense expense){
        ExpenseList list = ExpenseList.get();
        list.removeExpense(expense);

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Expenses");
        query.getInBackground(expense.getID(), new GetCallback<ParseObject>() {
            public void done(ParseObject object, ParseException e) {
                if (e == null) {
                    object.deleteInBackground();
                    Log.d(Context.class.toString(), "Expense deleted");
                } else {
                    Log.d(Context.class.toString(), "Expense not found!");
                }
            }
        });
    }

    /**
     * Updates the the list of users from the current list on parse.
     */
    public static void getUserList (Context context){
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()){
            Toast.makeText(context, "No internet connection, try again later.", Toast.LENGTH_SHORT).show();
        } else {
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
    }

    /**
     * Gets the current user and reaps it into a user object
     * @return user containing the information of the current user.
     */
    public static User getCurrentUser(){
        return new User(ParseUser.getCurrentUser());
    }
}
