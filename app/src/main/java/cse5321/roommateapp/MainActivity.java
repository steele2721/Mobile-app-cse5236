package cse5321.roommateapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.Parse;
import com.parse.ParseInstallation;
import com.parse.ParseObject;

public class MainActivity extends AppCompatActivity {

    // setup debug buttons here

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Parse.initialize(this, "0AccGSCjdEpz2LiGqe1ddTkbyaNKsSipSSDScuNF", "xi5bxKwxIF21mxRXowDbwNu8RecUcKJcOYV3TZsf");
        ParseObject.registerSubclass(Grocery.class);
        ParseInstallation.getCurrentInstallation().saveInBackground();

        Button groceryButton = (Button) findViewById(R.id.grocery_activity);
        final Context thisContext = this;
        groceryButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(thisContext, GroceryListActivity.class));
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

    public void ExpenseButtonClick(View v) {
        Intent intent = new Intent(this, ExpenseActivity.class);
        startActivity(intent);
    }

}
