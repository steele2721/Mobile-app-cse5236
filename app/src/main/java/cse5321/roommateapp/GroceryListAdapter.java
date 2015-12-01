package cse5321.roommateapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.Locale;

/**
 * Created by ryan on 11/8/15.
 */
public class GroceryListAdapter extends ArrayAdapter<Grocery> {
    private ArrayList<Grocery> mCheckedItems;

    public GroceryListAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
        mCheckedItems = new ArrayList<>();
    }

    public GroceryListAdapter(Context context, int resource, List<Grocery> items) {
        super(context, resource, items);
        mCheckedItems = new ArrayList<>();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.grocery_item_layout, null);
        }

        final Grocery item = getItem(position);

        if (item != null) {
            // get textviews
            TextView groceryNameView = (TextView) v.findViewById(R.id.grocery_item_name);

            // set activity for when the user clicks on a grocery item
            groceryNameView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder summaryBuilder = new AlertDialog.Builder(v.getContext());

                    if (item.getPrice() == -1.0) {
                        summaryBuilder.setMessage("Added by: "
                                + item.getAddedBy() + "\nFor: "
                                + item.getIsFor() + "Quantity: "
                                + item.getQuantity());
                    } else {
                        summaryBuilder.setMessage("Added by: "
                                + item.getAddedBy() + "\nFor: "
                                + item.getIsFor() + "\nQuantity: "
                                + item.getQuantity() + "\nPrice: "
                                + NumberFormat.getCurrencyInstance().format(item.getPrice()));
                    }

                    summaryBuilder.setTitle(item.getName());

                    summaryBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // doesn't need to do anything!
                        }
                    });

                    summaryBuilder.setNeutralButton("Edit", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // TODO: make it so that you can edit this item
                            Intent i = new Intent(getContext(), NewGroceryActivity.class);
                            Bundle b = new Bundle();
                            b.putString("EXTRA_GROCERY_NAME", item.getName());
                            b.putString("EXTRA_GROCERY_ADDEDBY", item.getAddedBy());
                            b.putString("EXTRA_GROCERY_ISFOR", item.getIsFor());
                            b.putInt("EXTRA_GROCERY_QUANTITY", item.getQuantity());
                            b.putString("EXTRA_GROCERY_ID", item.getID());

                            if (item.getPrice() != -1) {
                                b.putDouble("EXTRA_GROCERY_PRICE", item.getPrice());
                            }

                            i.putExtra("EXTRA_GROCERY", b);
                            getContext().startActivity(i);
                        }
                    });

                    AlertDialog summaryDialog = summaryBuilder.create();
                    summaryDialog.show();
                }
            });

            TextView groceryQuantityView = (TextView) v.findViewById(R.id.grocery_item_quantity);

            // set up checkboxes
            final CheckBox groceryCheckBox = (CheckBox) v.findViewById(R.id.grocery_item_checkbox);

            groceryCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked && !mCheckedItems.contains(item)) {
                        mCheckedItems.add(item);
                    } else if (!isChecked && mCheckedItems.contains(item)) {
                        mCheckedItems.remove(item);
                    }
                }
            });

            if (groceryNameView != null) {
                groceryNameView.setText(item.getName());
            }

            if (groceryQuantityView != null) {
                groceryQuantityView.setText(Integer.toString(item.getQuantity()));
            }
        }

        return v;

    }

    public ArrayList<Grocery> getCheckedPositions() {
        return mCheckedItems;
    }
}