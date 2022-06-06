package com.cst2335.androidlabs2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Coding from here
        setContentView(R.layout.activity_main_linear);

        SharedPreferences prefs = getSharedPreferences(PREFERENCES_FILE, Context.MODE_PRIVATE);
        String previous = prefs.getString("ReserveName", "Default Value");
        TextView reverseMessage = findViewById(R.id.reserved_name);
        reverseMessage.setText(previous);

        EditText editEmail = findViewById(R.id.editEmail);

        Button login = findViewById(R.id.login);
        login.setOnClickListener(clk -> {
            SharedPreferences.Editor writer = prefs.edit();
            writer.putString("ReserveName", editEmail.getText().toString());
            writer.apply(); //save to disk
            finish();
        });



       /** Button btn = findViewById(R.id.button2);
        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), getResources().getString(R.string.toast_message),Toast.LENGTH_LONG).show();
            }
        });

        Switch swt = findViewById(R.id.switch2);
        swt.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton cb, boolean isChecked) {
                View parentLayout = findViewById(android.R.id.content);
                Snackbar sb;
                if (isChecked) {
                    //sb = Snackbar.make(parentLayout, "switch is on", Snackbar.LENGTH_LONG);
                    sb = Snackbar.make(parentLayout,getResources().getString(R.string.switchOnMessage), Snackbar.LENGTH_LONG);
                    swt.setBackgroundColor(Color.parseColor("#FF80DFB0"));
                }
                else {
                    sb = Snackbar.make(parentLayout,getResources().getString(R.string.switchOffMessage), Snackbar.LENGTH_LONG);
                    swt.setBackgroundColor(Color.parseColor("white"));
                }
                sb.setAction(getResources().getString(R.string.undoMessage), click -> cb.setChecked(!isChecked));
                sb.show();
            }
        });*/


    }

}