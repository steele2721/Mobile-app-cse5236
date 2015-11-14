package cse5321.roommateapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Adapter to help generate the expense list.
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

        // TODO: add more fields!! Format to look nice!
        if (item != null) {
            TextView expenseNameView = (TextView) v.findViewById(R.id.expense_item_name);

            if (expenseNameView != null) {
                expenseNameView.setText(item.getName());
            }
        }

        return v;
    }

}