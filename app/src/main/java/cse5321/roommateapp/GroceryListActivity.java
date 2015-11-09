package cse5321.roommateapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.List;

public class GroceryListActivity extends AppCompatActivity {
    private List<Grocery> mGroceries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grocery_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mGroceries = GroceryList.get(this).getGroceryList();

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.grocery_list_fragment);

        if (fragment == null) {
            fragment = new GroceryListActivityFragment();
            fm.beginTransaction().add(R.id.grocery_list_fragment, fragment).commit();
        }

    }

}
