package cse5321.roommateapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
import android.widget.Toast;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

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

        final EditText mExpenseName = (EditText) view.findViewById(R.id.new_expense_name);
        final Spinner mExpensePaidBy = (Spinner) view.findViewById(R.id.edit_expense_piadby);
        final Spinner mExpenseType = (Spinner) view.findViewById(R.id.new_expense_type);
        final EditText mExpenseDueDate = (EditText) view.findViewById(R.id.new_expense_date);
        final EditText mExpenseAmount = (EditText) view.findViewById(R.id.new_expense_price);
        Button mSubmitButton = (Button) view.findViewById(R.id.submit);

        Calendar c = Calendar.getInstance();
        String date = (c.get(Calendar.MONTH) + 1) + "/" + c.get(Calendar.DATE) + "/" + c.get(Calendar.YEAR);
        mExpenseDueDate.setText(date);

        List<User> userList = UserList.get().getUserList();
        String[] users = new String[userList.size()];
        for (int i = 0; i < userList.size(); i++) {
            users[i] = userList.get(i).getFirstName();
        }

        String[] types = {
                "Rent",
                "Utility",
                "Groceries",
                "Other",
                "Settle debt"
        };

        mExpensePaidBy.setAdapter(new ArrayAdapter<>(getContext(), R.layout.choose_user_spinner, users));
        mExpenseType.setAdapter(new ArrayAdapter<>(getContext(), R.layout.choose_user_spinner, types));

        mExpenseAmount.addTextChangedListener(new TextWatcher() {
            String priceCurrent = "";
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // no op
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().equals(priceCurrent)){
                    mExpenseAmount.removeTextChangedListener(this);

                    String cleanString = s.toString().replaceAll("[$,.]", "");

                    double parsed = Double.parseDouble(cleanString);
                    String formatted = NumberFormat.getCurrencyInstance().format((parsed/100));

                    priceCurrent = formatted;
                    mExpenseAmount.setText(formatted);
                    mExpenseAmount.setSelection(formatted.length());

                    mExpenseAmount.addTextChangedListener(this);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                // no op
            }
        });

        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = mExpenseName.getText().toString().trim();
                String paidBy = mExpensePaidBy.getSelectedItem().toString().trim();
                String strPrice = mExpenseAmount.getText().toString().trim().substring(1);
                String dueDate = mExpenseDueDate.getText().toString().trim();
                String type = mExpenseType.getSelectedItem().toString().trim();

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
                        double price;
                        try {
                            price = NumberFormat.getInstance(Locale.getDefault()).parse(strPrice).doubleValue();
                        } catch (ParseException e) {
                            Log.d("ParseError", "Price couldn't be parsed. Setting it to default.");
                            price = 0.0;
                        }
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
    }
}
