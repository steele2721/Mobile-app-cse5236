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
    String userNameTxt;
    String passwordTxt;
    String firstNameTxt;
    String lastNameTxt;
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
                userNameTxt = username.getText().toString();
                passwordTxt = password.getText().toString();
                firstNameTxt = firstName.getText().toString();
                lastNameTxt = lastName.getText().toString();

                // Force user to fill up the form
                if (userNameTxt.equals("") || passwordTxt.equals("") || firstNameTxt.equals("") || lastNameTxt.equals("")) {
                    Toast.makeText(getApplicationContext(),
                            "Please complete the sign up form",
                            Toast.LENGTH_LONG).show();
                } else {

                    // Save new user data into Parse.com Data Storage
                    User user = new User();
                    user.setUserName(userNameTxt);
                    user.setPassword(passwordTxt);
                    user.setFirstName(firstNameTxt);
                    user.setLastName(lastNameTxt);
                    user.setAmountOwed(0);
                    user.saveUser(getApplicationContext());
                    finish();
                }
            }
        });
    }
}