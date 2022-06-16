package com.cst2335.androidlabs2;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {

    Activity context;
    ArrayList<Message> messages;

    public MyAdapter(ArrayList<Message> oMessages, Activity ctx) {
        messages = oMessages;
        context = ctx;
    }

    public int getCount() { return messages.size();}

    public Message getItem(int position) { return messages.get(position); }

    public long getItemId(int position) { return getItem(position).getId(); }

    public View getView(int position, View view, ViewGroup parent)
    {

        MessageViewHolder holder = new MessageViewHolder();
        View newView;
        LayoutInflater inflater = context.getLayoutInflater();
        Message message = getItem(position);

        //make a new row:
//        newView = inflater.inflate(R.layout.row_layout, parent, false);

        if(message.getIsSend()) {
            newView = inflater.inflate(R.layout.layout_send, null);
            holder.chatMsg = newView.findViewById(R.id.messageContent2);
            newView.setTag(holder);
            holder.chatMsg.setText(message.getMessageTyped());
        } else {
            newView = inflater.inflate(R.layout.layout_receive, null);
            holder.chatMsg = newView.findViewById(R.id.messageContent);
            newView.setTag(holder);
            holder.chatMsg.setText(message.getMessageTyped());
        }

        //return it to be put in the table
        return newView;
    }

    private class MessageViewHolder {
        public TextView chatMsg;
    }
}
