package cse5321.roommateapp;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.TextUtils;
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
                String name = groceryName.getText().toString().trim();
                String isFor = groceryIsFor.getText().toString().trim();
                String strQuantity = groceryQuantity.getText().toString().trim();
                String strPrice = groceryPrice.getText().toString().trim();

                if (name.isEmpty()) {
                    Toast.makeText(getContext(), "Please enter the name of this grocery item!", Toast.LENGTH_SHORT).show();
                } else if (isFor.isEmpty()) {
                    Toast.makeText(getContext(), "Please enter who this grocery item is for!", Toast.LENGTH_SHORT).show();
                } else if (strQuantity.isEmpty()) {
                    Toast.makeText(getContext(), "Please enter a quantity!", Toast.LENGTH_SHORT).show();
                } else {
                    int quantity = Integer.parseInt(strQuantity);

                    Grocery item;

                    if (!strPrice.isEmpty()) {
                        double price = Double.parseDouble(strPrice);
                        item = new Grocery(name, "ryan", isFor, price, quantity);
                    } else {
                        item = new Grocery(name, "ryan", isFor, quantity);
                    }

                    GroceryList.get().addGrocery(item);
                    ParseHelper.updateGroceryList(getContext(), item);
                    getActivity().finish();
                }
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
