package com.example.demo.EventPart.Controller;

import com.example.demo.EventPart.Controller.Responses.ActivityResponses;
import com.example.demo.EventPart.Controller.Responses.DeleteResponse;
import com.example.demo.EventPart.Controller.Responses.EventResponse;
import com.example.demo.EventPart.Repos.EventRepo;
import com.example.demo.EventPart.RequestForms.DayRequest;
import com.example.demo.EventPart.RequestForms.EventIDRequest;
import com.example.demo.EventPart.RequestForms.EventRequest;
import com.example.demo.EventPart.Service.ActivityService;
import com.example.demo.Regsitration.Entities.User;
import com.example.demo.Regsitration.Repos.UserRepo;
import com.example.demo.Regsitration.Services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class EventController {

    private final ActivityService activityService;

    private final AuthService authenticationService;

    private final UserRepo userRepo ;

    private final EventRepo eventRepo;

    @PostMapping("/addEvent")
    public ResponseEntity<ActivityResponses> addEvent(@RequestBody EventRequest eventRequest){

        User user = userRepo.findByEmail(authenticationService.getAuthInfo().getName()).get();


        return ResponseEntity.ok(activityService.addEvent(eventRequest, user.getId()));
    }


    @PatchMapping("/finishEvent")
    public ResponseEntity<ActivityResponses> finishEvent(@RequestBody EventIDRequest eventIDRequest){

        User user = userRepo.findByEmail(authenticationService.getAuthInfo().getName()).get();

        return ResponseEntity.ok(activityService.setNonActive(eventIDRequest, user.getId()));
    }


    @GetMapping("/getAllEvents")
    public ResponseEntity<List<EventResponse>> getAllUserEvents(){

        User user = userRepo.findByEmail(authenticationService.getAuthInfo().getName()).get();

        return ResponseEntity.ok(activityService.getAllEventsByUserID(user.getId()));
    }



    @GetMapping("/getAllEventsByDay")
    public ResponseEntity<List<EventResponse>> getAllUserEventsByDate(@RequestBody DayRequest dayRequest){

        User user = userRepo.findByEmail(authenticationService.getAuthInfo().getName()).get();

        return ResponseEntity.ok(activityService.getAllEventsByUserIDByDay(user.getId(), dayRequest.getDate()));
    }



    @DeleteMapping("/deleteEventByID")
    public ResponseEntity<DeleteResponse> deleteUserEventByEventID(@RequestBody EventIDRequest request){

        User user = userRepo.findByEmail(authenticationService.getAuthInfo().getName()).get();

        return ResponseEntity.ok(activityService.deleteEvent(user.getId(), request.getEventID()));
    }


}
