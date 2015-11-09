package cse5321.roommateapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

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

        Grocery item = getItem(position);

        // TODO: add more fields!!
        if (item != null) {
            TextView groceryNameView = (TextView) v.findViewById(R.id.grocery_item_name);

            if (groceryNameView != null) {
                groceryNameView.setText(item.getName());
            }
        }

        return v;
    }

}