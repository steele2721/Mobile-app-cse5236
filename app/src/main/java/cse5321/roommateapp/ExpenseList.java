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

    public static ExpenseList get() {
        if (sExpenseList == null) {
            sExpenseList = new ExpenseList();
        }

        return sExpenseList;
    }

    public ExpenseList() {
        mExpenseList = new ArrayList<>();
    }

    public void addExpense(Expense expense) {

        UserList users = UserList.get();
        for(User user : users){
            if (user.getName().equals(expense.getPaidBy())){
                user.setAmountPaid(user.getAmountPaid() + expense.getPrice());
            } else {
                double amount = user.getAmountOwed();
                user.setAmountOwed(amount + (1 / users.size()) * expense.getPrice());
            }
        }
        mExpenseList.add(expense);

    }

    public void removeExpense(Expense expense, Context context) {
        UserList users = UserList.get();
        for(User user : users){
            if (user.getName().equals(expense.getPaidBy())){
                user.setAmountPaid(user.getAmountPaid() - expense.getPrice());
            } else {
                double amount = user.getAmountOwed();
                user.setAmountOwed(amount - (1 / users.size()) * expense.getPrice());
            }
        }
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
}
