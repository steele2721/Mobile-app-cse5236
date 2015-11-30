package cse5321.roommateapp;


import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

/**
 * Fragment that displays list of expenses
 */
public class ExpenseListActivityFragment extends Fragment {
    private ListView mListView;
    private List<Expense> mExpenses;
    private ExpenseListAdapter mAdapter;

    public ExpenseListActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_expense_list, container, false);

        mExpenses = ExpenseList.get().getExpenseList();
        mListView = (ListView) v.findViewById(R.id.expense_list_view);
        mAdapter = new ExpenseListAdapter(getActivity(), R.id.expense_list_view, mExpenses);
        mListView.setAdapter(mAdapter);
        return v;
    }

    public void updateListView() {
        ParseHelper.getExpenseList();
        mExpenses = ExpenseList.get().getExpenseList();
        mListView = (ListView) getActivity().findViewById(R.id.expense_list_view);
        mAdapter = new ExpenseListAdapter(getActivity(), R.id.expense_list_view, mExpenses);
        mListView.setAdapter(mAdapter);
    }
}
