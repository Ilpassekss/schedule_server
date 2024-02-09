package com.example.demo.EventPart.Controller.Responses;

public class BadResponse implements ActivityResponses{

    String response;

    public BadResponse(String response) {
        this.response = response;
    }

    @Override
    public void setResponse(String response) {
        this.response = response;

    }
}
