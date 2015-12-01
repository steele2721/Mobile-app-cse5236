package cse5321.roommateapp;

import com.parse.ParseACL;
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
     * Takes an expense to be added to the list, and sets the ACL for all users.
     * @param expense The Expense to be added to the list
     */
    public void addExpense(Expense expense) {
        mExpenseList.add(expense);
    }

    /**
     * Removes the given expense from the list and from Parse.com
     *  expense muct be from the given list of expenses
     *
     *  @param expense the expense to be removed
     */
    public void removeExpense(Expense expense) {
        mExpenseList.remove(expense);
    }

    public Expense getExpense(String id) {
        for (Expense e : mExpenseList) {
            if (e.getID().equals(id)) {
                return e;
            }
        }

        return null;
    }

    /**
     * Rebuilds the list of expenses from Parse.
     * @param objects the list of ParseObjects used to rebuild the list
     */
    public void recreate(List<ParseObject> objects) {

        mExpenseList = new ArrayList<>();
        double listSize = UserList.get().size();
        User user = ParseHelper.getCurrentUser();
        double amountOwed = 0;

        UserList users = UserList.get();
        List<User> userList = users.getUserList();
        for (ParseObject object : objects) {
            Expense expense = new Expense(object);
            if (user.getFirstName().equals(expense.getPaidBy())){
                amountOwed = amountOwed - ((listSize - 1) / listSize) * expense.getPrice();
            } else {
                amountOwed = amountOwed + (1 / listSize) * expense.getPrice();
            }
            addExpense(expense);

            ParseACL groupACL = new ParseACL();
            for (User u : userList) {
                groupACL.setReadAccess(u.getParseUser(), true);
                groupACL.setWriteAccess(u.getParseUser(), true);
            }
            object.setACL(groupACL);
            object.saveInBackground();
        }
        user.setAmountOwed(amountOwed);
        user.updateUser();
    }

    /**
     * Returns the list of expenses to interact with in the code
     * @return list of expenses
     */
    public List<Expense> getExpenseList() {
        return mExpenseList;
    }
}
