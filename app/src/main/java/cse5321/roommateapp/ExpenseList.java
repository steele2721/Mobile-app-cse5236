package cse5321.roommateapp;

import android.content.Context;

import com.parse.ParseObject;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * List that holds the Expense objects
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

    public void addGrocery(Expense expense) {
        mExpenseList.add(expense);
    }

    public boolean contains(Expense expense) {
        return mExpenseList.contains(expense);
    }

    public void removeExpense(Expense expense) {
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

    public void recreate(List<ParseObject> objects) {

        mExpenseList = new ArrayList<>();
        for (ParseObject object : objects) {
            Expense expense = new Expense(object);
            mExpenseList.add(expense);
        }
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
