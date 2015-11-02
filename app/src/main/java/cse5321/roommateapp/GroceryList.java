package cse5321.roommateapp;

import android.content.Context;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by ryan on 10/25/15.
 */
public class GroceryList {
    private static GroceryList sGroceryList;
    private List<Grocery> mGroceryList;

    public static GroceryList get(Context context) {
        if (sGroceryList == null) {
            sGroceryList = new GroceryList(context);
        }

        return sGroceryList;
    }

    private GroceryList(Context context) {
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

    public Grocery getGrocery(UUID id) {
        for (Grocery grocery : mGroceryList) {
            if (grocery.getID().equals(id)) {
                return grocery;
            }
        }
        return null;
    }


}
