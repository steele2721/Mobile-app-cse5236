package cse5321.roommateapp;

import android.content.Context;
import android.util.Log;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.List;

/**
 * Created by John on 11/2/2015.
 */
public class ParseHelper {

    public static void getGroceryList (final Context context){

        ParseQuery<Grocery> query = ParseQuery.getQuery("Grocery");
        query.findInBackground(new FindCallback<Grocery>() {
            public void done(List<Grocery> objects, ParseException e) {
                if (e == null) {
                    for (Grocery grocery : objects) {
                        GroceryList groceryList = GroceryList.get(context);
                        Log.d(Context.class.toString(), "Getting the GroceryList.");
                        if (!groceryList.contains(grocery)){
                            groceryList.addGrocery(grocery);
                        } else {
                            groceryList.removeGrocery(grocery);
                            groceryList.addGrocery(grocery);
                        }
                    }
                } else {
                    Log.e(Context.class.toString(), e.toString());
                }
            }
        });
    }

    public static void updateGroceryList (final Context context,final Grocery newGrocery){

        ParseQuery<Grocery> query = ParseQuery.getQuery("Grocery");
        query.findInBackground(new FindCallback<Grocery>() {
            public void done(List<Grocery> objects, ParseException e) {
                if (e == null) {
                    Log.d(Context.class.toString(), "Updating the Grocery List.");
                    if (objects.contains(newGrocery)){
                        objects.remove(newGrocery);
                    }
                    newGrocery.saveInBackground();
                } else {
                    Log.e(Context.class.toString(), e.toString());
                }
            }
        });
    }

    public static void addGrocery(Grocery grocery){
        grocery.saveInBackground();
    }

    public static void getExpenseList (final Context context){

        ParseQuery<Expense> query = ParseQuery.getQuery("Expense");
        query.findInBackground(new FindCallback<Expense>() {
            public void done(List<Expense> objects, ParseException e) {
                if (e == null) {
                    for (Expense expense : objects) {
                        ExpenseList expenseList = ExpenseList.get(context);
                        Log.d(Context.class.toString(), "Getting the Expense List.");
                        expenseList.addExpense(expense);
                    }
                } else {
                    Log.e(Context.class.toString(), e.toString());
                }
            }
        });
    }

    public static void updateExpenseList (final Context context, final Expense newExpense){

        ParseQuery<Expense> query = ParseQuery.getQuery("Expense");
        query.findInBackground(new FindCallback<Expense>() {
            public void done(List<Expense> objects, ParseException e) {
                if (e == null) {
                    Log.d(Context.class.toString(), "Updating the Expense List.");
                    if (objects.contains(newExpense)) {
                        objects.remove(newExpense);
                    }
                    newExpense.saveInBackground();
                } else {
                    Log.e(Context.class.toString(), e.toString());
                }
            }
        });
    }

    public static void addExpense(Expense expense){
        expense.saveInBackground();
    }


}
