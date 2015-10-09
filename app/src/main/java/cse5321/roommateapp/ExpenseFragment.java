package cse5321.roommateapp;



//For Android 3.0 and above comment out the lines below
import android.support.v4.app.Fragment;

//For Android 3.0 and above uncomment the lines below
// import android.app.Fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ExpenseFragment extends Fragment implements OnClickListener{
    private EditText etUsername;
    private EditText etPassword;
    private EditText etConfirm;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_expense_activity_summary, container, false);
        return v;
    }

    public void onClick(View v) {

    }
}
