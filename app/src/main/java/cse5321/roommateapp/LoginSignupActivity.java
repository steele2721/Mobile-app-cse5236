package cse5321.roommateapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class LoginSignupActivity extends Activity {
    // Declare Variables
    Button loginButton;
    Button signup;
    String usernameTxt;
    String passwordTxt;
    EditText password;
    EditText username;

    /** Called when the activity is first created. */
    public void onCreate(Bundle savedInstanceState) {
        final Context thisContext = this;

        super.onCreate(savedInstanceState);
        // Get the view from main.xml
        setContentView(R.layout.loginsignup);
        // Locate EditTexts in main.xml
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);

        // Locate Buttons in main.xml
        loginButton = (Button) findViewById(R.id.login);
        signup = (Button) findViewById(R.id.signup);

        // Login Button Click Listener
        loginButton.setOnClickListener(new OnClickListener() {

            public void onClick(View arg0) {
                // Retrieve the text entered from the EditText
                usernameTxt = username.getText().toString();
                passwordTxt = password.getText().toString();

                // Send data to Parse.com for verification
                ParseUser.logInInBackground(usernameTxt, passwordTxt,
                        new LogInCallback() {
                            public void done(ParseUser user, ParseException e) {
                                if (user != null) {
                                    // If user exist and authenticated, send user to Welcome.class
                                    Intent intent = new Intent(
                                            LoginSignupActivity.this,
                                            WelcomeActivity.class);
                                    startActivity(intent);
                                    Toast.makeText(getApplicationContext(),
                                            "Successfully Logged in",
                                            Toast.LENGTH_LONG).show();
                                    finish();
                                } else {
                                    Toast.makeText(
                                            getApplicationContext(),
                                            "No such user exist, please signup",
                                            Toast.LENGTH_LONG).show();
                                }
                            }
                        });
            }
        });

        // Sign up Button Click Listener
        signup.setOnClickListener(new OnClickListener() {
            public void onClick(View arg0) {
                Intent i = new Intent(thisContext, SignupActivity.class);
                startActivity(i);
            }
        });

    }
}