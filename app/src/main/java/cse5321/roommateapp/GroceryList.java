package cse5321.roommateapp;

import android.content.Context;


import com.parse.ParseObject;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * List that holds the grocery objects
 * Created by ryan on 10/25/15.
 */
public class GroceryList {
    private static GroceryList sGroceryList;
    private List<Grocery> mGroceryList;

    public static GroceryList get() {
        if (sGroceryList == null) {
            sGroceryList = new GroceryList();
        }

        return sGroceryList;
    }

    private GroceryList() {
        mGroceryList = new ArrayList<>();
    }

    public void addGrocery(Grocery grocery) {
        mGroceryList.add(grocery);
    }

    public boolean contains(Grocery grocery) {
        return mGroceryList.contains(grocery);
    }

    public void removeGrocery(Grocery grocery) {
        mGroceryList.remove(grocery);
    }

    public void recreate(List<ParseObject> objects) {
        mGroceryList = new ArrayList<>();
        for (ParseObject object : objects) {
            Grocery grocery = new Grocery(object);
            addGrocery(grocery);
        }
    }

    public Grocery getGrocery(Grocery grocery) {
        for (Grocery item : mGroceryList) {
            if (item.getID().equals(grocery.getID())) {
                return grocery;
            }
        }
        return null;
    }

    public List<Grocery> getGroceryList() {
        return mGroceryList;
    }
}
