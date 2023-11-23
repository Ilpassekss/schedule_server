package com.example.demo.EventPart.Entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public abstract class Activity {



    // you can not create event if this func return true
    public boolean checkDate(LocalDate checkDate) {
        return !checkDate.isBefore(LocalDate.now());
    }

    // you can not create event if this func return true
    public boolean checkTime(LocalDateTime startTime, LocalDateTime finishTime, LocalDateTime checkStartTime, LocalDateTime checkFinishTime) {
        return !(checkFinishTime.isBefore(startTime) || checkStartTime.isAfter(finishTime));
    }

    public static LocalDateTime parseStringToLocalDateTime(String date, String time){

        return LocalDateTime.parse(date+ "T" +time);
    }
}
