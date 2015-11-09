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

    public static void getGroceryList (){

        ParseQuery<Grocery> query = ParseQuery.getQuery("Grocery");
        query.findInBackground(new FindCallback<Grocery>() {
            public void done(List<Grocery> objects, ParseException e) {
                if (e == null) {
                    GroceryList groceryList = GroceryList.get();
                    groceryList.recreate(objects);
                    Log.d(Context.class.toString(), "Getting the GroceryList.");

                } else {
                    Log.e(Context.class.toString(), e.toString());
                }
            }
        });
    }

    public static void updateGroceryList (final Context context, final Grocery newGrocery){

        ParseQuery<Grocery> query = ParseQuery.getQuery("Grocery");
        query.findInBackground(new FindCallback<Grocery>() {
            public void done(List<Grocery> objects, ParseException e) {
                if (e == null) {
                    Log.d(Context.class.toString(), "Updating the Grocery List.");
                    if (objects.contains(newGrocery)) {
                        objects.remove(newGrocery);
                    }
                    newGrocery.saveInBackground();

                } else {
                    Log.e(Context.class.toString(), e.toString());
                }
            }
        });


    }

    public static void getExpenseList (){

        ParseQuery<Expense> query = ParseQuery.getQuery("Expense");
        Log.d(Context.class.toString(), "Starting Expense Query.");
        query.findInBackground(new FindCallback<Expense>() {
            @Override
            public void done(List<Expense> objects, ParseException e) {
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
