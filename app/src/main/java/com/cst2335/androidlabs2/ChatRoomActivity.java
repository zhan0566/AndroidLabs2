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
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setTitle("Do you want to delete this?")

                    //What is the message:
                    .setMessage("The selected row is " + position + "\n" +
                            "The database id is: " + id)

                    //what the Yes button does:
                    .setPositiveButton("Yes", (click, arg) -> {
                        messages.remove(position);
                        theAdapter.notifyDataSetChanged();
                    })
                    //What the No button does:
                    .setNegativeButton("No", (click, arg) -> { })


                    //You can add extra layout elements:
                    .setView(getLayoutInflater().inflate(R.layout.row_layout, null) )

                    //Show the dialog
                    .create().show();
            return true;
        });

    }



    //this holds TextViews on a row:
//    public class MyViewHolder extends ListView.ViewHolder{
//      //  TextView timeView;
//        TextView messageView;
//        TextView receiveView;
//
//        //View will be a ConstraintLayout
//        public MyViewHolder(View itemView) {
//            super(itemView);
//
//            receiveView = itemView.findViewById(R.id.receiveMessage);
//          //timeView = itemView.findViewById(R.id.receiveMessage);
//            messageView = itemView.findViewById(R.id.sendMessage);
//
//            itemView.setOnClickListener( click -> {
//                int position = getAdapterPosition();//which row was clicked.
//                Message whatWasClicked = messages.get(position);
//
//                AlertDialog.Builder builder = new AlertDialog.Builder( ChatRoomActivity.this );
//
//                builder.setTitle("Question:")
//                        .setMessage("Do you want to delete this:" + whatWasClicked.getMessageTyped())
//                        .setNegativeButton("Negative", (dialog, click1)->{ })
//                        .setPositiveButton("Positive", (dialog, click2)->{
//                            //actually delete something:
//                            messages.remove(position);
//                            theAdapter.notifyItemRemoved(position);
//                        }).create().show();
//            });
//        }
//    }
    /** rView.setOnItemLongClickListener( (AdapterView<?> parent, View view, int position, long id) -> {
     AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
     alertDialogBuilder.setTitle("Make a choice")

     //What is the message:
     .setMessage("Do you want to add a row")

     //what the Yes button does:
     .setPositiveButton("Yes", (click, arg) -> {
     elements.add("HELLO");
     myAdapter.notifyDataSetChanged();
     })
     //What the No button does:
     .setNegativeButton("No", (click, arg) -> { })

     //An optional third button:
     .setNeutralButton("Maybe", (click, arg) -> {  })

     //You can add extra layout elements:
     .setView(getLayoutInflater().inflate(R.layout.row_layout, null) )

     //Show the dialog
     .create().show();
     return true;
     });
     */



}