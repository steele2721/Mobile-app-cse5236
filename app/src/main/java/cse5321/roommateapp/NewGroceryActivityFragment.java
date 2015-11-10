package cse5321.roommateapp;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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
        final EditText groceryName = (EditText) view.findViewById(R.id.new_grocery_name);
        final EditText groceryQuantity = (EditText) view.findViewById(R.id.new_grocery_quantity);
        final EditText groceryPrice = (EditText) view.findViewById(R.id.new_grocery_price);
        final EditText groceryIsFor = (EditText) view.findViewById(R.id.new_grocery_isfor);

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = groceryName.getText().toString();

                // TODO: fix these! this is unsafe!
                int quantity = Integer.parseInt(groceryQuantity.getText().toString());
                double price = Double.parseDouble(groceryPrice.getText().toString());

                String isFor = groceryIsFor.getText().toString();

                // TODO: for now we'll assume the user actually enters correct information
                Grocery item = new Grocery(name, "ryan", isFor, quantity);
                GroceryList.get().addGrocery(item);
                ParseHelper.updateGroceryList(getContext(), item);
                getActivity().finish();
            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
    }
}
