package cse5321.roommateapp;

import android.content.Context;
import android.util.Log;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

/**
 * Class for interfacing with Parse
 */
public class ParseHelper {

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

    public static void updateGrocery(Grocery newGrocery){
        GroceryList list = GroceryList.get();
        list.addGrocery(newGrocery);
        newGrocery.saveInBackground();
    }

    public static void removeGrocery (Grocery grocery){
        grocery.deleteInBackground();
    }


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

    public static void updateExpense (Expense newExpense){
        ExpenseList list = ExpenseList.get();
        list.addExpense(newExpense);
        newExpense.saveInBackground();
    }

    public static void removeExpense (Expense expense){
        expense.deleteInBackground();
    }

    public static void getUserList (){
        ParseQuery<ParseUser> query = ParseQuery.getQuery("User");
        Log.d(Context.class.toString(), "Starting User Query.");
        query.findInBackground(new FindCallback<ParseUser>() {
            @Override
            public void done(List<ParseUser> objects, ParseException e) {
                if (e == null) {
                    Log.d(Context.class.toString(), "Getting Expense List.");
                    UserList userList = UserList.get();
                    userList.recreate(objects);
                } else {
                    Log.e(Context.class.toString(), e.toString());
                }
            }
        });
    }

    public static User getCurentUser (){
        return new User(ParseUser.getCurrentUser());
    }
}
