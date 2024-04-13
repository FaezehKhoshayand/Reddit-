package org.example;

import java.util.ArrayList;

public class Chat {

    //Fields
    private ArrayList<Account> accounts;
    private ArrayList<Message> messages;

    //Constructor
    public Chat(Account a, Account b) {
        messages = new ArrayList<>();
        accounts = new ArrayList<>();
        accounts.add(a);
        accounts.add(b);
    }

    //Setters and Getters
    public ArrayList<Account> getAccounts() {
        return accounts;
    }
    public void addMessage(Message message) {
        messages.add(message);
    }

    //Display chat
    public void displayChat() {
        for (Message temp : messages)  {
            System.out.println(temp.getSender().getUsername() + ":" + temp.getMessage());
        }
    }
}
