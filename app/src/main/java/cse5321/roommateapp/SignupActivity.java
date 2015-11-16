package cse5321.roommateapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * Activity for creating a new user.
 */
public class SignupActivity extends Activity {
    // Declare Variables
    Button submit;
    String usernametxt;
    String passwordtxt;
    String firstNametxt;
    String lastNametxt;
    EditText password;
    EditText username;
    EditText firstName;
    EditText lastName;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_activity);

        // Locate EditTexts in main.xml
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        firstName = (EditText) findViewById(R.id.firstname);
        lastName = (EditText) findViewById(R.id.lastname);

        // Locate Buttons in main.xml
        submit = (Button) findViewById(R.id.submit);

        // Sign up Button Click Listener
        submit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                // Retrieve the text entered from the EditText
                usernametxt = username.getText().toString();
                passwordtxt = password.getText().toString();
                firstNametxt = firstName.getText().toString();
                lastNametxt = lastName.getText().toString();

                // Force user to fill up the form
                if (usernametxt.equals("") || passwordtxt.equals("") || firstNametxt.equals("") || lastNametxt.equals("")) {
                    Toast.makeText(getApplicationContext(),
                            "Please complete the sign up form",
                            Toast.LENGTH_LONG).show();
                } else {

                    // Save new user data into Parse.com Data Storage
                    User user = new User();
                    user.setUserName(usernametxt);
                    user.setPassword(passwordtxt);
                    user.setFirstName(firstNametxt);
                    user.setLastName(lastNametxt);
                    user.setAmountPaid(0);
                    user.setAmountOwed(0);
                    user.saveUser(getApplicationContext());
                    finish();
                }
            }
        });
    }
}