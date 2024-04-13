package org.example;

import java.util.ArrayList;

public class Message {

    //Fields
    private Account sender;
    private Account receiver;
    private String message;

    //Constructor
    Message(Account sender, Account receiver, String message) {
        this.sender = sender;
        this.receiver = receiver;
        this.message = message;
    }

    //Setters and Getters
    public String getMessage() {
        return message;
    }
    public Account getSender() {
        return sender;
    }
    public Account getReceiver() {
        return receiver;
    }
}
