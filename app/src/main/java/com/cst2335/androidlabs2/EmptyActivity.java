package com.cst2335.androidlabs2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.widget.FrameLayout;

public class EmptyActivity extends AppCompatActivity {

    DetailsFragment detailFragment;
    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empty);

        detailFragment = new DetailsFragment();
        detailFragment.setArguments(getIntent().getBundleExtra("bundle"));
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.flContainer, detailFragment);
        fragmentTransaction.commit();
    }
}