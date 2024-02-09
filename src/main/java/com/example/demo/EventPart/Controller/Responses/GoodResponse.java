package com.example.demo.EventPart.Controller.Responses;

public class GoodResponse implements ActivityResponses{

    String response ;

    public GoodResponse(String response) {
        this.response = response;
    }

    @Override
    public void setResponse(String response) {
        this.response = response;
    }
}
