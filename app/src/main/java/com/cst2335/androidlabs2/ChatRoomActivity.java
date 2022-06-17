package com.cst2335.androidlabs2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class ChatRoomActivity extends AppCompatActivity {
    Button submit;
    Button receive;
    EditText edit;
    ListView rView;
    BaseAdapter theAdapter;   //<<cannot be anonymous<<
    ArrayList<Message> messages = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //load XML
        setContentView(R.layout.activity_chat_room);

        receive =findViewById(R.id.receiveButton);
        submit = findViewById(R.id.submitButton);
        edit = findViewById(R.id.editText);
        rView = findViewById(R.id.myListView);

        theAdapter = new MyAdapter(messages, this);
        rView.setAdapter(theAdapter) ;
        //rView.setLayoutManager(new LinearLayoutManager(this));
        //rView.setLayoutManager(new GridLayoutManager (this, 2) );


        submit.setOnClickListener( click ->{
            String whatIsTyped = edit.getText().toString();
            //adding a new message to our history if not empty
            if ( !whatIsTyped.isEmpty()) {
                messages.add(new Message(whatIsTyped,true));
               // messages.add(new Message(whatIsTyped, currentDateandTime))

                //notify that new data was added at a row:

                theAdapter.notifyDataSetChanged(); //at the end of ArrayList,
                edit.setText("");//clear the text

            }
        });
        receive.setOnClickListener( v ->{
            String whatIsTyped = edit.getText().toString();
            //adding a new message to our history if not empty
            if ( !whatIsTyped.isEmpty()) {
                messages.add(new Message(whatIsTyped,false));
                // messages.add(new Message(whatIsTyped, currentDateandTime))

                //notify that new data was added at a row:

                theAdapter.notifyDataSetChanged(); //at the end of ArrayList,
                edit.setText("");//clear the text
            }
        });

        Button back = findViewById( R.id.backButton);
        back.setOnClickListener(  click ->  { finish(); } );

        rView.setOnItemLongClickListener( (AdapterView<?> parent, View view, int position, long id) -> {
            //Message whatWasClicked = messages.get(position);

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

            alertDialogBuilder.setTitle(R.string.delete)

                    //What is the message:
                    // .setMessage("The selected row is " + whatWasClicked.getMessageTyped() + "\n" +
                      // "The database id is: " + id)
                   .setMessage(getString(R.string.rowSelected)+ position + "\n" +
                            getString(R.string.idSelected)+ id)

                    //what the Yes button does:123
                    //.setPositiveButton("Positive",(dialog,click1)->{
                    //.setPositiveButton(getString(R.string.positive), (click, arg) -> {
                   .setPositiveButton(getString(R.string.positive), (click1, arg) -> {
                       messages.remove(position);
                       theAdapter.notifyDataSetChanged();
                    })
                    //What the No button does:
                    //.setNegativeButton("Negative",(dialog,click1) -> { })
                    .setNegativeButton(getString(R.string.negative), (click2, arg) -> { })
                   // .setNegativeButton(getString(R.string.negative), (click, arg) -> { })

                    //You can add extra layout elements:
                    .setView(getLayoutInflater().inflate(R.layout.row_layout, null) )

                    //Show the dialog
                    .create().show();
            return true;
        });

    }
}