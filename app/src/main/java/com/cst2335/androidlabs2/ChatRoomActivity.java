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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class ChatRoomActivity extends AppCompatActivity {
    Button submit;
    Button receive;
    EditText edit;
    RecyclerView rView;
    MyAdapter theAdapter;   //<<cannot be anonymous<<
    ArrayList<Message> messages = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //load XML
        setContentView(R.layout.activity_chat_room);

        receive =findViewById(R.id.receiveButton);
        submit = findViewById(R.id.submitButton);
        edit = findViewById(R.id.editText);
        rView = findViewById(R.id.myRecycleView);

        theAdapter = new MyAdapter();
        rView.setAdapter( theAdapter ) ;
        rView.setLayoutManager(new LinearLayoutManager(this));
        //rView.setLayoutManager(new GridLayoutManager (this, 2) );


        submit.setOnClickListener( click ->{
            String whatIsTyped = edit.getText().toString();
            //adding a new message to our history if not empty
            if ( !whatIsTyped.isEmpty()) {
                messages.add(new Message(whatIsTyped,true));
               // messages.add(new Message(whatIsTyped, currentDateandTime));

                edit.setText("");//clear the text

                //notify that new data was added at a row:
                theAdapter.notifyItemInserted(messages.size() - 1); //at the end of ArrayList,

            }
        });
        receive.setOnClickListener( click ->{
            String whatIsTyped = edit.getText().toString();
            //adding a new message to our history if not empty
            if ( !whatIsTyped.isEmpty()) {
                messages.add(new Message(whatIsTyped,false));
                // messages.add(new Message(whatIsTyped, currentDateandTime));
                edit.setText("");//clear the text

                //notify that new data was added at a row:
                theAdapter.notifyItemInserted(messages.size() - 1); //at the end of ArrayList,
            }
        });

        Button back = findViewById( R.id.backButton);
        back.setOnClickListener(  click ->  { finish(); } );
    }

    public class MyAdapter extends RecyclerView.Adapter< MyViewHolder > {

        //It inflates the view hierarchy
        //and creates an instance of the ViewHolder class
        //initialized with the view hierarchy before
        //returning it to the RecyclerView.
        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            //Load a new row from the layout file:
            LayoutInflater li = getLayoutInflater();

            //import layout for a row:
            View thisRow = li.inflate(R.layout.message, parent, false);

            return new MyViewHolder( thisRow );
        }


        //Populates the view hierarchy within the ViewHolder object
        //with the data to be displayed.
        //It is passed the ViewHolder object and an integer
        //value indicating the list item that is to be displayed.
        //This data is then displayed on the layout views using the references
        //created in the constructor method of the ViewHolder class
        //initializes a Row at position in the data array
        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) { //need an ArrayList to hold all the messages.
            //MyViewHolder has time and message textViews

            // What message object is at position:
            Message thisRow = messages.get(position);//

            //                      String object:
            //  holder.timeView.setText( thisRow.getTimeSent() );//what time goes on row position
            if (thisRow.getIsSend()) {
                holder.messageView.setText(thisRow.getMessageTyped());//what message goes on row position
            } else {
                holder.receiveView.setText(thisRow.getMessageTyped()); //what message goes on row position
            }
        }

        //returns the number of items in the array
        @Override
        public int getItemCount() {
            return messages.size() ; //how many items in the list
        }
    }

    //this holds TextViews on a row:
    public class MyViewHolder extends RecyclerView.ViewHolder{
      //  TextView timeView;
        TextView messageView;
        TextView receiveView;

        //View will be a ConstraintLayout
        public MyViewHolder(View itemView) {
            super(itemView);

            receiveView = itemView.findViewById(R.id.receiveMessage);
          //timeView = itemView.findViewById(R.id.receiveMessage);
            messageView = itemView.findViewById(R.id.sendMessage);

            itemView.setOnClickListener( click -> {
                int position = getAdapterPosition();//which row was clicked.
                Message whatWasClicked = messages.get(position);

                AlertDialog.Builder builder = new AlertDialog.Builder( ChatRoomActivity.this );

                builder.setTitle("Question:")
                        .setMessage("Do you want to delete this:" + whatWasClicked.getMessageTyped())
                        .setNegativeButton("Negative", (dialog, click1)->{ })
                        .setPositiveButton("Positive", (dialog, click2)->{
                            //actually delete something:
                            messages.remove(position);
                            theAdapter.notifyItemRemoved(position);
                        }).create().show();
            });
        }
    }

    public class Message{
        private String messageTyped;
        private Boolean isSend;
        public Message(String messageTyped , boolean isSend) {
       // public Message(String messageTyped, String timeSent) {
            this.messageTyped = messageTyped;
            this.isSend = isSend;
           // this.timeSent = timeSent;
        }

        public String getMessageTyped() {
            return messageTyped;
        }
        public boolean getIsSend(){return isSend;}
       // public String getTimeSent() {return timeSent;}
    }

}