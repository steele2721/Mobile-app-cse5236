package cse5321.roommateapp;

import android.support.v4.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.parse.Parse;
import com.parse.ParseInstallation;


public class MainActivity extends FragmentActivity{

    private static String TAG = "QuizActivity";

    private Button mexpenseActivityButton;

    private Button mgroceryActivityButton;

    private Button mnewExpenseButton;



    // setup debug buttons here

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate(Bundle) called ");
        setContentView(R.layout.activity_main);
        Parse.initialize(this, "0AccGSCjdEpz2LiGqe1ddTkbyaNKsSipSSDScuNF", "xi5bxKwxIF21mxRXowDbwNu8RecUcKJcOYV3TZsf");
        ParseInstallation.getCurrentInstallation().saveInBackground();
        mnewExpenseButton = (Button)findViewById(R.id.new_expense);
        FragmentManager fm = getSupportFragmentManager();

        // Inflates New Expense Activity when pressed
        mnewExpenseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, NewExpenseActivity.class);
                startActivity(i);
            }
        });

        mgroceryActivityButton = (Button)findViewById(R.id.grocery_activity);
        mgroceryActivityButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //Intent i = new Intent(MainActivity.this, Expe.this);
            }
        });

//        mgroceryActivityButton = (Button)findViewById(R.id.grocery_activity);
//        mgroceryActivityButton.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v){
//                Intent i = new Intent(MainActivity.this, ExpenseActivity.this);
//                startActivity(i);
//            }
//        });
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



//    public void ExpenseButtonClick(View v) {
//        Intent intent = new Intent(this, ExpenseActivity.class);
//        startActivity(intent);
//    }
//
//    @Override
//    public void onStart() {
//        super.onStart();
//        Log.d(TAG, "onStart() called");
//    }
//
//    @Override
//    public void onPause() {
//        super.onPause();
//        Log.d(TAG, "onPause() called");
//    }
//
//    @Override
//    public void onResume() {
//        super.onResume();
//        Log.d(TAG, "onResume() called");
//    }
//
//    @Override
//    public void onStop() {
//        super.onStop();
//        Log.d(TAG, "onStop() called");
//    }
//
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        Log.d(TAG, "onDestroy() called");
//    }


}
