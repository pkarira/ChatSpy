package com.example.chatspy.Parsing_Classes;

import java.util.ArrayList;

public class Contact {
    private String address;
    private ArrayList<Message> messages;

    public Contact() {
        messages = new ArrayList<>();
    }

    public String getAddress() {
        return address;
    }
    public ArrayList<Message> getMessages() {
        return messages;
    }
    public void setAddress(String address) {
        this.address=address;
    }
    public void addMessage (Message msg) {
        messages.add(0,msg);
    }
}
