package cse5321.roommateapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.NumberFormat;
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

        final Expense item = getItem(position);

        if (item != null) {
            TextView expenseNameView = (TextView) v.findViewById(R.id.expense_item_name);
            TextView expenseAmountView = (TextView) v.findViewById(R.id.expense_item_amount);

            if (expenseNameView != null) {
                expenseNameView.setText(item.getName());

                expenseNameView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder summaryBuilder = new AlertDialog.Builder(v.getContext());

                        summaryBuilder.setMessage("Type: "
                                + item.getType() + "\nPaid by: "
                                + item.getPaidBy() + "\nAmount: "
                                + NumberFormat.getCurrencyInstance().format(item.getPrice())
                                + "\nDue date: " + item.getDueDate());

                        summaryBuilder.setTitle(item.getName());

                        summaryBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // doesn't need to do anything!
                            }
                        });

                        AlertDialog summaryDialog = summaryBuilder.create();
                        summaryDialog.show();
                    }
                });
            }

            if (expenseAmountView != null) {
                expenseAmountView.setText(NumberFormat.getCurrencyInstance().format(item.getPrice()));
            }
        }

        return v;
    }

}