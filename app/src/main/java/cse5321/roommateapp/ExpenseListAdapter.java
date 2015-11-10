package cse5321.roommateapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * USed for populating the Expense listview
 * Created by ryan on 11/8/15.
 */
public class ExpenseListAdapter extends ArrayAdapter<Expense> {

    public ExpenseListAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public ExpenseListAdapter(Context context, int resource, List<Expense> items) {
        super(context, resource, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.expense_item_layout, null);
        }

        Expense item = getItem(position);

        // TODO: add more fields!!
        if (item != null) {
            TextView groceryNameView = (TextView) v.findViewById(R.id.expense_item_name);

            if (groceryNameView != null) {
                groceryNameView.setText(item.getName());
            }
        }

        return v;
    }

}