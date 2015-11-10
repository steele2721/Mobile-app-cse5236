package cse5321.roommateapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Date;

public class NewExpenseActivity extends AppCompatActivity {

    EditText mExpenseName;

    EditText mExpensePaidBy;

    EditText mExpensetype;

    EditText mExpenseDueDate;

    EditText mExpenseAmount;

    Button mSubmitButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_expense);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mExpenseDueDate = (EditText) findViewById(R.id.edit_expense_duedate);
        mExpenseAmount = (EditText) findViewById(R.id.edit_expense_amount);
        mExpensePaidBy = (EditText) findViewById(R.id.edit_expense_piadby);
        mExpensetype = (EditText) findViewById(R.id.edit_expense_type);
        mSubmitButton = (Button) findViewById(R.id.submit_button);

        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                double expenseAmount = Double.valueOf(mExpenseAmount.getText().toString());
                Date expenseDueDate = (Date)mExpenseDueDate.getText();
                String expensePaidBy = mExpensePaidBy.getText().toString();
                String expenseName = mExpenseName.getText().toString();
                String expenseType = mExpensetype.getText().toString();
                Expense expense = new Expense(expenseName, expensePaidBy, expenseType,expenseAmount, expenseDueDate);


            }
        });


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();


            }
        });

    }
}