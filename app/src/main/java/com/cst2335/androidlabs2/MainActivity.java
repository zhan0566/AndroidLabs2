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
        setContentView(R.layout.activity_main);
        Button btn = findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Here is more information",Toast.LENGTH_LONG).show();
            }
        });



        /**Switch swt = findViewById(R.id.switch1);
        swt.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
                Toast.makeText(getApplicationContext(),"Swith is now", Toast.LENGTH_LONG).show();


                View parentLayout = findViewById(android.R.id.content);
                Snackbar sb;
                if(isChecked){
                    Toast.makeText(getApplicationContext(),"Swith is on", Toast.LENGTH_LONG).show();
                    swt.setBackgroundColor(Color.parseColor("#FF80DFB0"));
                }
                else {
                    Toast.makeText(getApplicationContext(),"Swith is off", Toast.LENGTH_LONG).show();
                    swt.setBackgroundColor(Color.parseColor("white"));
                }

                Snackbar.make(buttonView,"Switch is now",Snackbar.LENGTH_LONG)
                        .setAction("UNDO", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                            }
                        });
            }
        });*/
        Switch sw = (Switch) findViewById(R.id.switch1);
        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton cb, boolean isChecked) {
                View parentLayout = findViewById(android.R.id.content);
                Snackbar sb;
                if (isChecked) {
                    sb = Snackbar.make(parentLayout, "switch is on", Snackbar.LENGTH_LONG);
                }
                else {
                    sb = Snackbar.make(parentLayout, "switch is off", Snackbar.LENGTH_LONG);
                }
                sb.setAction("undo", click -> cb.setChecked(!isChecked));
                sb.show();

            }
        });





    }
}