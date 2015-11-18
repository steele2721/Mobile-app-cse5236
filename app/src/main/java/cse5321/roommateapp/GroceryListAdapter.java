package cse5321.roommateapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;
import java.util.Currency;
import java.util.List;
import java.util.Locale;

/**
 * Created by ryan on 11/8/15.
 */
public class GroceryListAdapter extends ArrayAdapter<Grocery> {

    public GroceryListAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public GroceryListAdapter(Context context, int resource, List<Grocery> items) {
        super(context, resource, items);
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
                    Log.d("Click", "Thanks for clicking me!");
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
                            Log.d("Dialog", "You clicked the positive button!");
                        }
                    });

                    AlertDialog summaryDialog = summaryBuilder.create();
                    summaryDialog.show();
                }
            });

            TextView groceryQuantityView = (TextView) v.findViewById(R.id.grocery_item_quantity);

            // set up checkboxes
            final CheckBox groceryCheckBox = (CheckBox) v.findViewById(R.id.grocery_item_checkbox);
            groceryCheckBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    groceryCheckBox.setChecked(groceryCheckBox.isChecked());
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
}