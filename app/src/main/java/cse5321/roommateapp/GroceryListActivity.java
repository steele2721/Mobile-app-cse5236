package cse5321.roommateapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

/**
 * Activity that displays the GroceryList
 */
public class GroceryListActivity extends AppCompatActivity {
    private GroceryListActivityFragment mGroceryListActivityFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grocery_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FragmentManager fm = getSupportFragmentManager();
        mGroceryListActivityFragment = (GroceryListActivityFragment) fm.findFragmentById(R.id.grocery_list_fragment);

        if (mGroceryListActivityFragment == null) {
            mGroceryListActivityFragment = new GroceryListActivityFragment();
            fm.beginTransaction().add(R.id.grocery_list_fragment, mGroceryListActivityFragment).commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_grocery_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_new_grocery:
                Intent intent = new Intent(this, NewGroceryActivity.class);
                startActivityForResult(intent, 0);
                return true;

            case R.id.action_refresh_grocery_list:
                mGroceryListActivityFragment.updateListView();
                return true;

            default:
                // definitely shouldn't happen!
                return super.onOptionsItemSelected(item);

        }
    }

}
