package com.cst2335.androidlabs2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;


public class DetailsFragment extends Fragment {

    private static final String ARG_PARAM1 = "message";
    private static final String ARG_PARAM2 = "id";
    private static final String ARG_PARAM3 = "isSend";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        String message = null;
        long id = 0;
        boolean isSend = false;
        if(getArguments() != null) {
            message = getArguments().getString(ARG_PARAM1);
            id = getArguments().getLong(ARG_PARAM2);
            isSend = getArguments().getBoolean(ARG_PARAM3);
        }

        View newView;
        newView = inflater.inflate(R.layout.fragment_blank, container, false);
        TextView textView1 = newView.findViewById(R.id.textView1);
        textView1.setText(message);
        TextView textView2 = newView.findViewById(R.id.textView2);
        textView2.setText("ID = " + id);
        CheckBox checkBox2 = newView.findViewById(R.id.checkBox2);
        checkBox2.setChecked(isSend);

        newView.findViewById(R.id.hide_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getActivity() != null) {
                    FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.remove(DetailsFragment.this);
                    fragmentTransaction.commit();
                }
            }
        });

        return newView;
    }
}