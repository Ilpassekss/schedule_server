package com.example.demo.EventPart.Controller.Responses;


import lombok.*;

@Data
@Builder
@NoArgsConstructor

public class AddResponse extends ActivityResponses{

    private String title;

    private String userID;

    private final String response = ActivityResponses.OK_RESP;

    public AddResponse(String title, String userID) {
        this.title = title;
        this.userID = userID;
    }
}
