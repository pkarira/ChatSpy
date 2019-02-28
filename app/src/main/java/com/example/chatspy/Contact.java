package com.example.chatspy;

import java.util.ArrayList;

public class Contact {
    private String address;
    private ArrayList<Message> sent;
    private ArrayList<Message> received;

    Contact() {
        sent = new ArrayList<>();
        received = new ArrayList<>();
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address=address;
    }

    public void addSent(Message msg) {
        sent.add(msg);
    }
    public void addReceived(Message msg) {
        received.add(msg);
    }
}
