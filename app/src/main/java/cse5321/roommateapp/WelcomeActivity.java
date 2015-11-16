package cse5321.roommateapp;


import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.parse.ParseInstallation;
import com.parse.ParseUser;

public class WelcomeActivity extends AppCompatActivity {
    private ExpenseListActivityFragment mExpenseListActivityFragment;
    private GroceryListActivityFragment mGroceryListActivityFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        // Retrieve current user from Parse.com
//        ParseUser currentUser = ParseUser.getCurrentUser();
        // Convert currentUser into String
//        String struser = currentUser.getUsername().toString();
        ParseInstallation.getCurrentInstallation().saveInBackground();

        FragmentManager fm = getSupportFragmentManager();
        mGroceryListActivityFragment = (GroceryListActivityFragment) fm.findFragmentById(R.id.grocery_list_fragment);
        mExpenseListActivityFragment = (ExpenseListActivityFragment) fm.findFragmentById(R.id.expense_list_fragment);

        if (mGroceryListActivityFragment == null) {
            mGroceryListActivityFragment = new GroceryListActivityFragment();
            fm.beginTransaction().add(R.id.grocery_list_fragment, mGroceryListActivityFragment).commit();
        }

        if (mExpenseListActivityFragment == null) {
            mExpenseListActivityFragment = new ExpenseListActivityFragment();
            fm.beginTransaction().add(R.id.expense_list_fragment, mExpenseListActivityFragment).commit();
        }


        // Inflates Grocery Activity when pressed
        Button mGroceryActivityButton = (Button)findViewById(R.id.grocery_activity);
        mGroceryActivityButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(WelcomeActivity.this, GroceryListActivity.class));
            }
        });

        // Inflates Expense Activity when pressed
        Button mExpenseActivityButton = (Button) findViewById(R.id.expense_activity);
        mExpenseActivityButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(WelcomeActivity.this, ExpenseActivity.class));
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

        switch (item.getItemId()) {
            //noinspection SimplifiableIfStatement
            case R.id.action_sign_out:
                ParseUser.logOut();
                finish();
                return true;
            case R.id.action_refresh:
                mGroceryListActivityFragment.updateListView();
                mExpenseListActivityFragment.updateListView();
                return true;
            default:
                // definitely shouldn't happen!
                return super.onOptionsItemSelected(item);
        }
    }
}
