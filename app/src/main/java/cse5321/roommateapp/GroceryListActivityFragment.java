package cse5321.roommateapp;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class GroceryListActivityFragment extends Fragment {
    private ListView mListView;
    private List<Grocery> mGroceries;

    public GroceryListActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_grocery_list, container, false);

        mGroceries = GroceryList.get().getGroceryList();

        mListView = (ListView) v.findViewById(R.id.grocery_list_view);
        mListView.setAdapter(new GroceryListAdapter(getActivity(), R.id.grocery_list_view, mGroceries));
        return v;
    }
}
