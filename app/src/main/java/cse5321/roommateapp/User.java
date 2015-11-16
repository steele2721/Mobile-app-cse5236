package cse5321.roommateapp;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;


import com.parse.ParseClassName;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import java.util.UUID;

/**
 *  The User Object to be used with the application.
 * Created by John on 11/6/2015.
 */
public class User {
    private UUID mID;
    private ParseUser user;

    /**
     * Default constructor
     */
    public User() {
        user = new ParseUser();
    }

    /**
     * Creates a new reference to the user from the ParseUser
     * @param parseUser the Parse Object used to create the user
     */
    public User(ParseUser parseUser) {
        user = parseUser;
    }

    // setter methods

    /**
     * sets the user name of the user
     * @param value the user name of the user
     */
    public void setUserName(String value) {
        user.setUsername(value);
    }

    /**
     * sets the password of the user
     * @param value password of the user
     */
    public void setPassword(String value) {
        user.setPassword(value);
    }

    /**
     * sets the first name of the user
     * @param value first name of the user
     */
    public void setFirstName(String value) {
        user.put("FirstName", value);
    }

    /**
     * sets the last name of the user
     * @param value last name of the user
     */
    public void setLastName(String value) {
        user.put("LastName", value);
    }

    /**
     * sets the amount that the user has paid
     * @param value amount that the user has paid
     */
    public void setAmountPaid(double value) {
        user.put("AmountPaid", value);
    }

    /**
     * sets the amount that the user has borrowed
     * @param value amount that the user has borrowed
     */
    public void setAmountOwed(double value) {
        user.put("AmountOwed", value);
    }

    // getter methods

    /**
     * Gets the user name of the user
     * @return the user name of the user
     */
    public String getUserName() {
        return user.getUsername();
    }

    /**
     * Gets the first name of the user
     * @return the first name of the user
     */
    public String getFirstName() {
        return user.getString("FirstName");
    }

    /**
     * Gets the last name of the user
     * @return the last name of the user
     */
    public String getLastName() {
        return user.getString("LastName");
    }

    /**
     * gets the amount that the user has paid
     * @return amount that the user has paid
     */
    public double getAmountPaid() {
        return user.getInt("AmountPaid");
    }

    /**
     * gets the amount that the user has borrowed
     * @return amount that the user has borrowed
     */
    public double getAmountOwed() {
        return user.getInt("AmountOwed");
    }

    /**
     * Saves the new user to parse
     * @param context the context of the activity calling this method
     */
    public void saveUser(Context context) {
        final Context c = context;
        user.signUpInBackground(new SignUpCallback() {
            public void done(ParseException e) {
                if (e == null) {
                    // Show a simple Toast message upon successful registration
                    Toast.makeText(c,
                            "Successfully Signed up, please log in.",
                            Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(c,
                            "Sign up Error", Toast.LENGTH_LONG)
                            .show();
                }
            }
        });
    }

    /**
     * Updates the user information in parse
     */
    public void updateUser() {
        user.signUpInBackground(new SignUpCallback() {
            public void done(ParseException e) {
                if (e == null) {
                    // Show a simple Toast message upon successful registration
                    Log.d("User.updateUser", "User update successful.");
                } else {
                    Log.d("User.updateUser", "User update failed.");
                }
            }
        });
    }
}
