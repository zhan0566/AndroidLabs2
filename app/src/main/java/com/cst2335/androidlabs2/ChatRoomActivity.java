package com.cst2335.androidlabs2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class ChatRoomActivity extends AppCompatActivity {
    MyOpenHelper myOpener;
    SQLiteDatabase  theDatabase;
    Button submit;
    Button receive;
    EditText edit;
    ListView rView;
    BaseAdapter theAdapter;   //<<cannot be anonymous<<
    ArrayList<Message> messages = new ArrayList<>();
    public final static String TAG ="ChatRoomActivity";
    boolean isTablet = false;

    DetailsFragment detailFragment;
    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //load XML
        //Log.i(TAG, "In onCreate, creating the objects");
        setContentView(R.layout.activity_chat_room);

        isTablet = findViewById(R.id.flContainer) != null;

        if(isTablet) {
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.flContainer, new DetailsFragment());
            fragmentTransaction.commit();
        }
       else{
            Intent nextPage = new Intent(ChatRoomActivity.this,  EmptyActivity.class  );
            startActivity(nextPage);

        }





        myOpener = new MyOpenHelper(this);
        theDatabase = myOpener.getWritableDatabase();
        Cursor results = theDatabase.rawQuery("Select * from " + MyOpenHelper.TABLE_NAME + ";", null);

        int version = theDatabase.getVersion();
        int idIndex = results.getColumnIndex(MyOpenHelper.COL_ID);
        int messageIndex = results.getColumnIndex(MyOpenHelper.COL_MESSAGE);
        int sOrRIndex = results.getColumnIndex(MyOpenHelper.COL_SEND_RECEIVE);


            while (results.moveToNext()) {
                long id = results.getInt(idIndex);
                String message = results.getString(messageIndex);
                boolean isSend = (1 == results.getInt(sOrRIndex));
                messages.add(new Message(message, isSend, id));
            }

        printCursor(results, version, idIndex, messageIndex, sOrRIndex);


        receive = findViewById(R.id.receiveButton);
        submit = findViewById(R.id.submitButton);
        edit = findViewById(R.id.editText);
        rView = findViewById(R.id.myListView);

        theAdapter = new MyAdapter(messages, this);
        rView.setAdapter(theAdapter);

        submit.setOnClickListener(click -> {
            String whatIsTyped = edit.getText().toString();
            //adding a new message to our history if not empty
            if (!whatIsTyped.isEmpty()) {
                ContentValues newRow = new ContentValues();// like intent or Bundle
                //Message column:
                newRow.put(MyOpenHelper.COL_MESSAGE, whatIsTyped);

                //Send or receive column:
                newRow.put(MyOpenHelper.COL_SEND_RECEIVE, 1);

                long id = theDatabase.insert(MyOpenHelper.TABLE_NAME, null, newRow);
                messages.add(new Message(whatIsTyped, true, id));
                // messages.add(new Message(whatIsTyped, currentDateandTime))

                //notify that new data was added at a row:

                theAdapter.notifyDataSetChanged(); //at the end of ArrayList,
                edit.setText("");//clear the text

            }
        });
        receive.setOnClickListener(v -> {
            String whatIsTyped = edit.getText().toString();
            //adding a new message to our history if not empty
            if (!whatIsTyped.isEmpty()) {
                ContentValues newRow = new ContentValues();// like intent or Bundle

                //Message column:
                newRow.put(MyOpenHelper.COL_MESSAGE, whatIsTyped);

                //Send or receive column:
                newRow.put(MyOpenHelper.COL_SEND_RECEIVE, 2);

                long id = theDatabase.insert(MyOpenHelper.TABLE_NAME, null, newRow);

                messages.add(new Message(whatIsTyped, false, id));
                // messages.add(new Message(whatIsTyped, currentDateandTime))

                //notify that new data was added at a row:

                theAdapter.notifyDataSetChanged(); //at the end of ArrayList,
                edit.setText("");//clear the text
            }
        });

        Button back = findViewById(R.id.backButton);
        back.setOnClickListener(click -> {
            finish();
        });
        rView.setOnItemClickListener((AdapterView<?> parent, View view, int position, long id) -> {
        //rView.setOnItemLongClickListener((AdapterView<?> parent, View view, int position, long id) -> {
            Message whatWasClicked = messages.get(position);

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ChatRoomActivity.this);

            alertDialogBuilder.setTitle(R.string.delete)

                    //What is the message:
                    .setMessage(getString(R.string.rowSelected) + position + "\n" +
                            getString(R.string.idSelected) + id)


                    .setNegativeButton(getString(R.string.negative), (click, arg) -> {
                    })

                    //what the Yes button does:

                    .setPositiveButton(getString(R.string.positive), (click, arg) -> {
                        // messages.remove(messages.size()-1);
                        messages.remove(position);
                        theAdapter.notifyDataSetChanged();
                        //delete from database:, returns number of rows deleted
                        theDatabase.delete(MyOpenHelper.TABLE_NAME,
                                MyOpenHelper.COL_ID + " = ?", new String[]{Long.toString(whatWasClicked.getId())});
                    })
                    //Show the dialog
                    .create().show();
           // return true;
        });

    }
    public void printCursor(Cursor c, int version, int idIndex, int messageIndex, int sOrRIndex) {
        c.moveToFirst();
        Log.i(TAG, "Version Number" + String.valueOf(version));
        int numberOfColumns = c.getColumnCount();
        Log.i(TAG, "Number of Columns:" + String.valueOf(numberOfColumns));
        for (int i = 0; i < numberOfColumns; i++) {
            Log.i(TAG, "Name of Column " + String.valueOf(i) + c.getColumnName(i));
        }
        int numberOfRows = c.getCount();
        Log.i(TAG, "Number of Rows: " + String.valueOf(numberOfRows));

       /** if (numberOfRows > 0) {
            Log.v(TAG, "ID " + c.getString(idIndex));
            Log.v(TAG, "Message " + c.getString(messageIndex));
            Log.v(TAG, "Send or Receive" + c.getString(sOrRIndex));
        }*/
        while (c.moveToNext()) {
            Log.v(TAG, "ID " + c.getString(idIndex));
            Log.v(TAG, "Message " + c.getString(messageIndex));
            Log.v(TAG, "Send or Receive " + c.getString(sOrRIndex));
        }
    }
}
