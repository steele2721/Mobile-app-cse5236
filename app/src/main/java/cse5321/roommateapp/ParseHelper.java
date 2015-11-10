package cse5321.roommateapp;

import android.content.Context;
import android.util.Log;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

/**
 * Class for interfacing with Parse
 * Created by John on 11/2/2015.
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

    public static void updateGroceryList (Grocery newGrocery){
        GroceryList list = GroceryList.get();
        if (list.contains(newGrocery)){
            list.removeGrocery(newGrocery);
        }

        list.addGrocery(newGrocery);
        newGrocery.saveInBackground();
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

    public static void updateExpenseList (Expense newExpense){

        ExpenseList list = ExpenseList.get();
        if (list.contains(newExpense)){
            list.removeExpense(newExpense);
        }

        list.addExpense(newExpense);
        newExpense.saveInBackground();
    }

    public static void addExpense(Expense expense){
        expense.saveInBackground();
    }


}
