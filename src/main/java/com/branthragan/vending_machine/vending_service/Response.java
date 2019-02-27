package com.branthragan.vending_machine.vending_service;

public class Response {
    private String message;

    Response(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
