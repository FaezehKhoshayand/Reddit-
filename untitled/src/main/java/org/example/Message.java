package org.example;

import java.util.ArrayList;

public class Message {
    private Account sender;
    private Account receiver;
    private String message;
    Message(Account sender, Account receiver, String message) {
        this.sender = sender;
        this.receiver = receiver;
        this.message = message;
    }
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
