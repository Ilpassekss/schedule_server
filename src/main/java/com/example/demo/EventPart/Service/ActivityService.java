package com.example.demo.EventPart.Service;

import com.example.demo.EventPart.Controller.Responses.*;
import com.example.demo.EventPart.Entities.Event;
import com.example.demo.EventPart.Repos.EventRepo;
import com.example.demo.EventPart.RequestForms.EventRequest;
import lombok.RequiredArgsConstructor;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ActivityService {

    private final EventRepo eventRepo;

    //add event into mongo db

    public ActivityResponses addEvent(EventRequest eventRequest, String userID) {

        LocalDateTime eventStartTime = Event.parseStringToLocalDateTime(eventRequest.getDate(), eventRequest.getStartTime());
        LocalDateTime eventFinishTime = Event.parseStringToLocalDateTime(eventRequest.getDate(), eventRequest.getFinishTime());
        LocalDateTime eventDay = Event.parseStringToLocalDateTime(eventRequest.getDate(), "02:00:00");


        if(eventStartTime.isAfter(eventFinishTime)){

            return  new BadResponse("Event start time(" + eventStartTime.toString()
                    + ") is after this event finish");
        } else if (eventDay.isBefore(LocalDateTime.now())) {

            return new BadResponse("You can not create events for the past time. Event date :"
                    + eventDay.toString() + " . Local date: " + LocalDate.now().toString());
        }

        //here i have to add multithreading
        for(Event event : eventRepo.findAllByDateAndUserID(eventDay, userID).get()){

            if(event.checkTime(event.getStartTime(), event.getFinishTime(), eventStartTime, eventFinishTime)){

                return new BadResponse("You have event at the same time");
            }

        }

       var event = Event.builder()
               .userID(userID)
               .date(eventDay)
               .startTime(eventStartTime)
               .finishTime(eventFinishTime)
               .title(eventRequest.getTitle())
               .description(eventRequest.getDescription())
               .build();

        event.setActive(true);

        eventRepo.save(event);



        return new GoodResponse("You added new activity");
    }

    //get All user events
    public List<EventResponse> getAllEventsByUserID(String userID){

        List<EventResponse> eventResponses = new ArrayList<>();

        for(Event event : eventRepo.findAllByUserID(userID).get()){
            eventResponses.add(EventResponse.builder()
                            .id(event.getId())
                            .userID(event.getUserID())
                            .date(event.getDate().toString())
                            .startTime(event.getStartTime().toString())
                            .finishTime(event.getFinishTime().toString())
                            .title(event.getTitle())
                            .description(event.getDescription())
                            .isActive(event.getActive())
                    .build());
        }

        return eventResponses;
    }

    //get all events by day
    public List<EventResponse> getAllEventsByUserIDByDay(String userID, String date){

        System.out.println(LocalDateTime.parse(date));

        List<EventResponse> eventResponses = new ArrayList<>();

        for(Event event : eventRepo.findAllByDateAndUserID(LocalDateTime.parse(date), userID).get() ){

            eventResponses.add(EventResponse.builder()
                    .id(event.getId())
                    .userID(event.getUserID())
                    .date(event.getDate().toString())
                    .startTime(event.getStartTime().toString())
                    .finishTime(event.getFinishTime().toString())
                    .title(event.getTitle())
                    .description(event.getDescription())
                    .build());
        }

        return eventResponses;
    }

    //delete event bu eventID
    public ActivityResponses deleteEvent(String userID, String id){

        eventRepo.deleteByIdAndUserID(id, userID);

        return new GoodResponse("Event was deleted successful");
    }

    public ActivityResponses setNonActive(String eventIDRequest, String userID){

        Event event = eventRepo.findByUserIDAndId(userID, eventIDRequest).get();

        if(event.getActive()){

            event.setActive(false);

            eventRepo.save(event);

            return new GoodResponse("Event finished successful");
        }else {
            return new BadResponse("Event " + eventIDRequest + " already finished");
        }
    }

    @Async
    @Scheduled(fixedRate = 60000)
    public void updateActivities(){

        List<Event> events = eventRepo.findAll();

        for(Event event : events){

            if(event.getFinishTime().isBefore(LocalDateTime.now()) && event.getActive()){

                event.setActive(false);

                eventRepo.save(event);
            }
        }
    }
}