package com.cst2335.androidlabs2;

public class Message {
    private String messageTyped;
    private Boolean isSend;
    protected long id;

    public Message(String messageTyped , boolean isSend, long id) {
        // public Message(String messageTyped, String timeSent) {
        this.messageTyped = messageTyped;
        this.isSend = isSend;
        this.id = id;
    }

    public Message(String messageTyped , boolean isSend) {
        this(messageTyped, isSend, 0);
    }

    public String getMessageTyped() {
        return messageTyped;
    }
    public boolean getIsSend(){return isSend;}
    public long getId() {return id;}
}
