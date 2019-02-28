package com.example.chatspy;

public class Message {
    private String msg;
    private String date;

    public void setType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    private int type;

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMsg() {
        return msg;
    }

    public String getDate() {
        return date;
    }
}
