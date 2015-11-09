package cse5321.roommateapp;



import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;

public class ExpenseActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense);

        //Create the fragment
        ExpenseFragment expenseFragment = new ExpenseFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        //For Android 3.0 and above uncomment the line below
        //FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.add(R.id.expense_list_fragment, expenseFragment);
        fragmentTransaction.commit();

    }

    public void SummaryButtonClick(View v) {
        Intent intent = new Intent(this, ExpenseSummaryActivity.class);
        startActivity(intent);
    }
}
