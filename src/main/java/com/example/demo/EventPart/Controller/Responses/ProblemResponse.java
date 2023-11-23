package com.example.demo.EventPart.Controller.Responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class ProblemResponse extends ActivityResponses{

    private String msg ;

    private final String response = ActivityResponses.BAD_RESP;

    public ProblemResponse(String msg) {
        this.msg = msg;
    }
}
