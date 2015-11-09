package cse5321.roommateapp;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by ryan on 11/2/15.
 */
public class ExpenseList {
    public static ExpenseList sExpenseList;
    public List<Expense> mExpenseList;

    public static ExpenseList get(Context context) {
        if (sExpenseList == null) {
            sExpenseList = new ExpenseList(context);
        }

        return sExpenseList;
    }

    public ExpenseList(Context context) {
        mExpenseList = new ArrayList<>();
    }

    public void addExpense(Expense expense) {
        mExpenseList.add(expense);
    }

    public void removeExpense(Expense expense) {
        mExpenseList.remove(expense);
    }

    public Expense getExpense(UUID id) {
        for (Expense expense : mExpenseList) {
            if (expense.getID().equals(id)) {
                return expense;
            }
        }

        return null;
    }

    public List<Expense> getExpenseList() {
        return mExpenseList;
    }
}
