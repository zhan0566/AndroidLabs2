package com.cst2335.androidlabs2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;

public class EmptyActivity extends AppCompatActivity {

    boolean isTablet = false;
    DetailsFragment detailFragment;
    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empty);

        isTablet = findViewById(R.id.flContainer) != null;

        if(isTablet) {
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.flContainer, new DetailsFragment());
            fragmentTransaction.commit();
        }
        else{
            Intent nextPage = new Intent(EmptyActivity.this,  ChatRoomActivity.class  );
            startActivity(nextPage);

        }






    }
}