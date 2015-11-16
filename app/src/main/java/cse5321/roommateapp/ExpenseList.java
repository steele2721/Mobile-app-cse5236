package cse5321.roommateapp;

import com.parse.ParseObject;

import java.util.ArrayList;
import java.util.List;

/**
 * List that holds the Expense objects
 */
public class ExpenseList {
    public static ExpenseList sExpenseList;
    public List<Expense> mExpenseList;

    /**
     * Returns the list of expenses stored on
     * @return ExpenseList
     */
    public static ExpenseList get() {
        if (sExpenseList == null) {
            sExpenseList = new ExpenseList();
        }
        return sExpenseList;
    }

    /**
     * Default Constructor
     */
    public ExpenseList() {
        mExpenseList = new ArrayList<>();
    }

    /**
     * Takes an expense to be added to the list, and splits the cost amoung all users.
     * @param expense The Expense to be added to the list
     */
    public void addExpense(Expense expense) {

        UserList users = UserList.get();
        for(User user : users){
            String name = user.getFirstName();
            if (name.equals(expense.getPaidBy())){
                user.setAmountPaid(user.getAmountPaid() + expense.getPrice());
                user.updateUser();
            } else {
                double amount = user.getAmountOwed();
                user.setAmountOwed(amount + (1 / users.size()) * expense.getPrice());
                user.updateUser();
            }
        }
        mExpenseList.add(expense);
    }

    /**
     * Removes the given expense from the list and from Parse.com
     *  expense muct be from the given list of expenses
     *
     *  @param expense the expense to be removed
     */
    public void removeExpense(Expense expense) {
        if (expense.getObjectId() != null) {
            UserList users = UserList.get();
            for (User user : users) {
                String name = user.getFirstName();
                if (name.equals(expense.getPaidBy())) {
                    user.setAmountPaid(user.getAmountPaid() - expense.getPrice());
                    user.updateUser();
                } else {
                    double amount = user.getAmountOwed();
                    user.setAmountOwed(amount - (1 / users.size()) * expense.getPrice());
                    user.updateUser();
                }
            }
            mExpenseList.remove(expense);
            expense.deleteInBackground();
        }
    }

    /**
     * Rebuilds the list of expenses from Parse.
     * @param objects the list of ParseObjects used to rebuild the list
     */
    public void recreate(List<ParseObject> objects) {

        mExpenseList = new ArrayList<>();
        for (ParseObject object : objects) {
            Expense expense = new Expense(object);
            mExpenseList.add(expense);
        }
    }

    /**
     * Returns the list of expenses to interact with in the code
     * @return list of expenses
     */
    public List<Expense> getExpenseList() {
        return mExpenseList;
    }
}
