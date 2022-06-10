package com.cst2335.androidlabs2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.backup.SharedPreferencesBackupHelper;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
      public final static String TAG ="MainActivity";
      public final static String PREFERENCES_FILE = "MyData";

    EditText editEmail;

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences  prefs = getSharedPreferences(PREFERENCES_FILE, Context.MODE_PRIVATE);
        //String previous = prefs.getString("ReserveName", "Default Value");
        SharedPreferences.Editor writer = prefs.edit();
        writer.putString("ReserveName", editEmail.getText().toString());
        writer.apply(); //save to disk
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Coding from here
        setContentView(R.layout.activity_main_linear);

       SharedPreferences prefs = getSharedPreferences(PREFERENCES_FILE, Context.MODE_PRIVATE);
        String previous = prefs.getString("ReserveName", "Default Value");

       // TextView reverseMessage = findViewById(R.id.reserved_name);
      //  reverseMessage.setText(previous);

        editEmail = findViewById(R.id.editEmail);

        Button login = findViewById(R.id.login);
        login.setOnClickListener(clk -> {
            Intent nextPage = new Intent(MainActivity.this,  ProfileActivity.class  );
            //Make the transition:
            nextPage.putExtra("email",editEmail.getText().toString());
            startActivity(nextPage);

           // finish();
        });
    }

}