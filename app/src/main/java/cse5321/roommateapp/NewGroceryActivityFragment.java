package cse5321.roommateapp;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.Parse;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.List;
import java.util.Locale;

/**
 * Fragment for creating a new grocery item
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

        final Spinner groceryIsFor = (Spinner) view.findViewById(R.id.new_grocery_isfor);
        final Spinner groceryAddedBy = (Spinner) view.findViewById(R.id.new_grocery_addedBy);

        List<User> userList = UserList.get().getUserList();
        String[] users = new String[userList.size()];
        for (int i = 0; i < userList.size(); i++) {
            users[i] = userList.get(i).getFirstName();
        }

        groceryIsFor.setAdapter(new ArrayAdapter<>(getContext(), R.layout.choose_user_spinner, users));
        groceryAddedBy.setAdapter(new ArrayAdapter<>(getContext(), R.layout.choose_user_spinner, users));

        Bundle bundle = getActivity().getIntent().getExtras();
        if (bundle != null) {
            Bundle b = bundle.getBundle("EXTRA_GROCERY");
            groceryName.setText(b.getString("EXTRA_GROCERY_NAME"));
            groceryQuantity.setText(Integer.toString(b.getInt("EXTRA_GROCERY_QUANTITY")));
            groceryPrice.setText(Double.toString(b.getDouble("EXTRA_GROCERY_PRICE")));

            createButton.setText("Update");
        } else {
            Log.d("NewGrocery", "Bundle is null!");
        }

        groceryPrice.addTextChangedListener(new TextWatcher() {
            String priceCurrent = "";
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // no op
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().equals(priceCurrent)){
                    groceryPrice.removeTextChangedListener(this);

                    String cleanString = s.toString().replaceAll("[$,.]", "");

                    double parsed = Double.parseDouble(cleanString);
                    String formatted = NumberFormat.getCurrencyInstance().format((parsed/100));

                    priceCurrent = formatted;
                    groceryPrice.setText(formatted);
                    groceryPrice.setSelection(formatted.length());

                    groceryPrice.addTextChangedListener(this);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                // no op
            }
        });

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = groceryName.getText().toString().trim();
                String isFor = groceryIsFor.getSelectedItem().toString().trim();
                String addedBy = groceryAddedBy.getSelectedItem().toString().trim();
                String strQuantity = groceryQuantity.getText().toString().trim();
                String strPrice = groceryPrice.getText().toString().trim().substring(1);

                if (name.isEmpty()) {
                    Toast.makeText(getContext(), "Please enter the name of this grocery item!", Toast.LENGTH_SHORT).show();
                } else if (isFor.isEmpty()) {
                    Toast.makeText(getContext(), "Please enter who this grocery item is for!", Toast.LENGTH_SHORT).show();
                } else if (strQuantity.isEmpty()) {
                    Toast.makeText(getContext(), "Please enter a quantity!", Toast.LENGTH_SHORT).show();
                } else {
                    int quantity = Integer.parseInt(strQuantity);

                    Grocery item = null;

                    Bundle bundle = getActivity().getIntent().getExtras();
                    if (bundle != null) {
                        item = GroceryList.get().getGrocery(bundle.getBundle("EXTRA_GROCERY").getString("EXTRA_GROCERY_ID"));
                    }

                    if (!strPrice.isEmpty()) {
                        double price;
                        try {
                            price = NumberFormat.getInstance(Locale.getDefault()).parse(strPrice).doubleValue();
                        } catch (ParseException e) {
                            Log.d("ParseError", "Price couldn't be parsed. Setting it to default.");
                            price = 0.0;
                        }

                        if (item != null) {
                            item.setName(name);
                            item.setIsFor(isFor);
                            item.setAddedBy(addedBy);
                            item.setQuantity(quantity);
                            item.setPrice(price);

                            ParseHelper.updateGrocery(item);

                        } else {
                            item = new Grocery(name, addedBy, isFor, price, quantity);
                            ParseHelper.addGrocery(item);
                        }
                    } else {
                        if (item != null) {
                            item.setName(name);
                            item.setIsFor(isFor);
                            item.setAddedBy(addedBy);
                            item.setQuantity(quantity);

                            ParseHelper.updateGrocery(item);

                        } else {
                            item = new Grocery(name, addedBy, isFor, quantity);
                            ParseHelper.addGrocery(item);
                        }
                    }

                    getActivity().finish();
                }
            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
        }
    }
}
