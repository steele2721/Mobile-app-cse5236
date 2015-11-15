package cse5321.roommateapp;

import android.content.Context;
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

    public User() {
        user = new ParseUser();
    }

    public UUID getID() {
        return mID;
    }

    // setter methods
    public void setUserName(String value) {
        user.setUsername(value);
    }

    public void setPassword(String value) {
        user.setPassword(value);
    }

    public void setFirstName(String value) {
        user.put("FirstName", value);
    }

    public void setLastName(String value) {
        user.put("LastName", value);
    }

    public void setAmountPaid(double value) {
        user.put("AmountPaid", value);
    }

    public void setAmountOwed(double value) {
        user.put("AmountOwed", value);
    }

    // getter methods
    public String getUserName() {
        return user.getUsername();
    }

    public String getFirstName() {
        return user.getString("FirstName");
    }

    public String getLastName() {
        return user.getString("LastName");
    }

    public double getAmountPaid() {
        return user.getInt("AmountPaid");
    }

    public double getAmountOwed() {
        return user.getInt("AmountOwed");
    }

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

}
