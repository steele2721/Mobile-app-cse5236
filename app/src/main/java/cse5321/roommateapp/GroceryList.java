package cse5321.roommateapp;


import android.util.Log;

import com.parse.ParseACL;
import com.parse.ParseObject;

import java.util.ArrayList;
import java.util.List;

/**
 * List that holds the grocery objects
 */
public class GroceryList {
    private static GroceryList sGroceryList;
    private List<Grocery> mGroceryList;

    /**
     * Returns the list of Groceries to use in coding
     * @return GroceryList that contains the groceries
     */
    public static GroceryList get() {
        if (sGroceryList == null) {
            sGroceryList = new GroceryList();
        }
        return sGroceryList;
    }

    /**
     * Default constructor
     */
    private GroceryList() {
        mGroceryList = new ArrayList<>();
    }

    /**
     * Adds a new grocery to the list, and sets the ACL for all users.
     * @param grocery the new grocery to be added
     */
    public void addGrocery(Grocery grocery) {
        mGroceryList.add(grocery);
    }

    /**
     * Removes the given grocery from the list and from Parse.com
     *  grocery muct be from the given list of expenses
     *
     *  @param grocery the grocery to be removed
     */
    public void removeGrocery(Grocery grocery) {
        Log.d("GroceryList", "Removed: " + grocery.getName());
        mGroceryList.remove(grocery);
    }

    /**
     * Rebuilds the list of groceries from Parse.
     * @param objects the list of ParseObjects used to rebuild the list
     */
    public void recreate(List<ParseObject> objects) {
        mGroceryList = new ArrayList<>();

        UserList users = UserList.get();
        List<User> userList = users.getUserList();
        for (ParseObject object : objects) {
            Grocery grocery = new Grocery(object);
            addGrocery(grocery);

            ParseACL groupACL = new ParseACL();
            for (User user : userList) {
                groupACL.setReadAccess(user.getParseUser(), true);
                groupACL.setWriteAccess(user.getParseUser(), true);
            }
            object.setACL(groupACL);
            object.saveInBackground();
        }
    }

    /**
     * Returns the list of groceries to interact with in the code
     * @return list of expenses
     */
    public List<Grocery> getGroceryList() {
        return mGroceryList;
    }
}
