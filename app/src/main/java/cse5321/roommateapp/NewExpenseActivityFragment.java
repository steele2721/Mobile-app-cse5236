package cse5321.roommateapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Fragment for creating a new expense item
 */
public class NewExpenseActivityFragment extends Fragment {

    public NewExpenseActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_new_expense, container, false);

        final EditText mExpenseName = (EditText) view.findViewById(R.id.edit_expense_name);
        final EditText mExpensePaidBy = (EditText) view.findViewById(R.id.edit_expense_piadby);
        final EditText mExpenseType = (EditText) view.findViewById(R.id.edit_expense_type);
        final EditText mExpenseDueDate = (EditText) view.findViewById(R.id.edit_expense_duedate);
        final EditText mExpenseAmount = (EditText) view.findViewById(R.id.edit_expense_amount);
        Button mSubmitButton = (Button) view.findViewById(R.id.submit_button);

        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = mExpenseName.getText().toString().trim();
                String paidBy = mExpensePaidBy.getText().toString().trim();
                String dueDate = mExpenseDueDate.getText().toString().trim();
                String strPrice = mExpenseAmount.getText().toString().trim();
                String type = mExpenseType.getText().toString().trim();

                if (name.isEmpty()) {
                    Toast.makeText(getContext(), "Please enter the name of this grocery item!", Toast.LENGTH_SHORT).show();
                } else if (paidBy.isEmpty()) {
                    Toast.makeText(getContext(), "Please enter who paid this expense!", Toast.LENGTH_SHORT).show();
                } else if (strPrice.isEmpty()) {
                    Toast.makeText(getContext(), "Please enter a amount of the expense!", Toast.LENGTH_SHORT).show();
                } else if (dueDate.isEmpty()) {
                Toast.makeText(getContext(), "Please enter the date the the expense is due!", Toast.LENGTH_SHORT).show();
                }  else if (type.isEmpty()) {
                Toast.makeText(getContext(), "Please enter the type of the expense!", Toast.LENGTH_SHORT).show();
                } else {

                    Expense item;
                    double price = Double.parseDouble(strPrice);
                    item = new Expense(name, paidBy, type, price, dueDate);

                    ParseHelper.updateExpense(item);
                    item.saveInBackground();
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
