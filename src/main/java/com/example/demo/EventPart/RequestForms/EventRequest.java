package com.example.demo.EventPart.RequestForms;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventRequest {

    private String date;

    private String startTime;
    private String finishTime;

    private String title;
    private String description;

}
