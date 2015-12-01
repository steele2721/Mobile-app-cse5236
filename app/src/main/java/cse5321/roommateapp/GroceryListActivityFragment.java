package cse5321.roommateapp;


import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.parse.Parse;

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
        ParseHelper.getGroceryList();
        mGroceries = GroceryList.get().getGroceryList();
        mListView = (ListView) v.findViewById(R.id.grocery_list_view);
        mAdapter = new GroceryListAdapter(getActivity(), R.id.grocery_list_view, mGroceries);
        mListView.setAdapter(mAdapter);
        mListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        return v;
    }

    public void updateListView() {
        ParseHelper.getGroceryList();
        mAdapter.notifyDataSetChanged();
        mGroceries = GroceryList.get().getGroceryList();
        mListView = (ListView) getActivity().findViewById(R.id.grocery_list_view);
        mAdapter = new GroceryListAdapter(getActivity(), R.id.grocery_list_view, mGroceries);
        mListView.setAdapter(mAdapter);
    }

    public void removeCheckedGroceries() {
        for (Grocery g : mAdapter.getCheckedPositions()) {
            GroceryList.get().removeGrocery(g);
            ParseHelper.removeGrocery(g);
        }
        updateListView();
    }
}
