package cse5321.roommateapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.parse.Parse;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.ParseUser;

public class WelcomeActivity extends AppCompatActivity {
    private Button mexpenseActivityButton;
    private Button mgroceryActivityButton;
    private Button mnewExpenseButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        // Retrieve current user from Parse.com
        ParseUser currentUser = ParseUser.getCurrentUser();

        // Convert currentUser into String
        String struser = currentUser.getUsername().toString();


        ParseObject.registerSubclass(Expense.class);
        ParseObject.registerSubclass(Grocery.class);
        ParseInstallation.getCurrentInstallation().saveInBackground();

        final Context thisContext = this;

        // Inflates New Expense Activity when pressed
        mnewExpenseButton = (Button)findViewById(R.id.new_expense);
        mnewExpenseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(thisContext, NewExpenseActivity.class);
                startActivity(i);
            }
        });

        // Inflates Grocery Activity when pressed
        mgroceryActivityButton = (Button)findViewById(R.id.grocery_activity);
        mgroceryActivityButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(thisContext, GroceryListActivity.class));
            }
        });

        // Inflates Expense Activity when pressed
        mexpenseActivityButton = (Button) findViewById(R.id.expense_activity);
        mexpenseActivityButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(thisContext, ExpenseActivity.class));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
