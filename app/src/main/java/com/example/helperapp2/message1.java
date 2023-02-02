package com.example.helperapp2;

public class message1 {
    String senderuid, message;

    public message1() {
    }

    public message1(String senderuid, String message) {
        this.senderuid = senderuid;
        this.message = message;
    }

    public String getSenderuid() {
        return senderuid;
    }

    public void setSenderuid(String senderuid) {
        this.senderuid = senderuid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
