package cse5321.roommateapp;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

/**
 * Fragment that displays list of groceries
 */
public class GroceryListActivityFragment extends Fragment {
    private ListView mListView;
    private List<Grocery> mGroceries;
    private GroceryListAdapter mAdapter;

    public GroceryListActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_grocery_list, container, false);

<<<<<<< HEAD
        mGroceries = GroceryList.get().getGroceryList();

=======
        List<Grocery> groceries = GroceryList.get().getGroceryList();
>>>>>>> f5ff1838589f2d2000155d9542aa13b2b9fb5ccd
        mListView = (ListView) v.findViewById(R.id.grocery_list_view);
        mAdapter = new GroceryListAdapter(getActivity(), R.id.grocery_list_view, mGroceries);
        mListView.setAdapter(mAdapter);
        return v;
    }

    public void updateListView() {
        mAdapter.notifyDataSetChanged();
        Toast.makeText(getContext(), "Refreshing grocery list", Toast.LENGTH_SHORT).show();
    }
}
