package com.example.demo.EventPart.Controller.Responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventResponse {

    private String id;

    private String userID;

    private String date;

    private String startTime;
    private String finishTime;

    private String title;
    private String description;

    private boolean isActive;

}
