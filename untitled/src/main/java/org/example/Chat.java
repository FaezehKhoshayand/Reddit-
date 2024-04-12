package org.example;

import java.util.ArrayList;

public class Chat {
    static ArrayList<Message> messages;
    public Chat() {
        messages = new ArrayList<>();
    }
    public void addMessage(Message message) {
        messages.add(message);
    }
    public static void displayChat(Account a, Account b) {
        for (Message temp : messages)  {
            System.out.println(temp.getSender() + ":" + temp.getMessage());
        }
    }
}
