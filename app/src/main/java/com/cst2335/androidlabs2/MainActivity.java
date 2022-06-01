package com.cst2335.androidlabs2;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_constraint);
        Button btn = findViewById(R.id.button2);
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
        });
    }
}