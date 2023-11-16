package com.example.demo.Data.Entities;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
abstract class Event {

    protected LocalDateTime startTime;
    protected LocalDateTime finishTime;

    protected String title;
    protected String description;

    protected final int points = 5;

    protected boolean isActive;

    public static boolean checkTimeForPast(LocalDateTime checkTime){

        if(checkTime.isBefore(LocalDateTime.now()))
            return false;
        else
            return true;
    }

    public static boolean checkFinishTime(LocalDateTime checkTime){

        if(checkTime.isBefore(LocalDateTime.now()))
            return false;
        else
            return true;
    }

    //public boolean

}
