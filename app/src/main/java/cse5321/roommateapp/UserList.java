package cse5321.roommateapp;


import android.util.Log;

import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

/**
 * List Class for Users. Holds all of the users.
 */
public class UserList extends ArrayList<User>{
    public static UserList sUserList;
    public List<User> mUserList;

    /**
     * Returns the list of users
     * @return the list of users.
     */
    public static UserList get() {
        Log.d("User List", "User List .get Created");
        if (sUserList == null) {
            sUserList = new UserList();
        }
        return sUserList;
    }

    /**
     * Default constructor
     */
    public UserList() {
       mUserList = new ArrayList<User>();
    }

    /**
     * The list list containing all of the users. This is one step below the UserList
     * @return List<User> containg the users
     */
    public List<User> getUserList() {
        return mUserList;
    }

    /**
     * Removes a user from the list
     * @param user the user to be removed
     */
    public void removeUser (User user) {
        mUserList.remove(user);
    }

    /**
     * Rebuilds the list of users from Parse.
     * @param objects the list of ParseObjects used to rebuild the list
     */
    public void recreate (List<ParseUser> objects){
        mUserList = new ArrayList<>();
        Log.d("User List", "Started Creating User List");
        for (ParseUser object : objects) {
            User user = new User(object);
            Log.d("User List", "User Created User List");
            mUserList.add(user);
        }
    }

    /**
     * Returns number of users in the list
     * @return number of users in the list
     */
    public int size (){
        return mUserList.size();
    }
}
