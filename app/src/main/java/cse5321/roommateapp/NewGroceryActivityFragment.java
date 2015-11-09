package cse5321.roommateapp;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

/**
 * A placeholder fragment containing a simple view.
 */
public class NewGroceryActivityFragment extends Fragment {

    public NewGroceryActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_new_grocery, container, false);

        Button createButton = (Button) view.findViewById(R.id.new_grocery_button);
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // this is a test object! 
                Grocery newItem = new Grocery("paper", "ryan", "ryan", 22);
                GroceryList.get(view.getContext()).addGrocery(newItem);
                newItem.saveInBackground();
            }
        });

        return view;
    }
}
