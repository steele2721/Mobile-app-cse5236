package cse5321.roommateapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

/**
 * Activity that displays the ExpenseList
 */
public class ExpenseActivity extends AppCompatActivity {
    private ExpenseListActivityFragment mExpenseListActivityFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FragmentManager fm = getSupportFragmentManager();
        mExpenseListActivityFragment = (ExpenseListActivityFragment) fm.findFragmentById(R.id.expense_list_fragment);

        if (mExpenseListActivityFragment == null) {
            mExpenseListActivityFragment = new ExpenseListActivityFragment();
            fm.beginTransaction().add(R.id.expense_list_fragment, mExpenseListActivityFragment).commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_expense_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_new_expense:
                Intent intent = new Intent(this, NewExpenseActivity.class);
                startActivityForResult(intent, 0);
                return true;

            case R.id.action_refresh_expense_list:
                mExpenseListActivityFragment.updateListView();
                return true;

            default:
                // definitely shouldn't happen!
                return super.onOptionsItemSelected(item);
        }
    }

}
