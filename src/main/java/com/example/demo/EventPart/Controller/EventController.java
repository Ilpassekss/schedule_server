package com.example.demo.EventPart.Controller;

import com.example.demo.EventPart.Controller.Responses.ActivityResponses;
import com.example.demo.EventPart.Controller.Responses.EventResponse;
import com.example.demo.EventPart.RequestForms.EventRequest;
import com.example.demo.EventPart.Service.ActivityService;
import com.example.demo.Regsitration.Entities.User;
import com.example.demo.Regsitration.Repos.UserRepo;
import com.example.demo.Regsitration.Services.AuthService;
import jakarta.validation.constraints.Pattern;
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


    @PostMapping("/addEvent")
    public ResponseEntity<ActivityResponses> addEvent(@RequestBody EventRequest eventRequest){

        User user = userRepo.findByEmail(authenticationService.getAuthInfo().getName()).get();


        return ResponseEntity.ok(activityService.addEvent(eventRequest, user.getId()));
    }

    //
    @PatchMapping("/finishEvent")
    public ResponseEntity<ActivityResponses> finishEvent(@RequestParam(name = "eventID") String eventIDRequest){

        User user = userRepo.findByEmail(authenticationService.getAuthInfo().getName()).get();

        return ResponseEntity.ok(activityService.setNonActive(eventIDRequest, user.getId()));
    }


    @GetMapping("/getAllEvents")
        public ResponseEntity<List<EventResponse>> getAllUserEvents(){

            User user = userRepo.findByEmail(authenticationService.getAuthInfo().getName()).get();

            return ResponseEntity.ok(activityService.getAllEventsByUserID(user.getId()));
    }


    //
    @GetMapping("/getAllEventsByDay")
    public ResponseEntity<List<EventResponse>> getAllUserEventsByDate(@RequestParam(name = "date")
                                                                          @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}")
                                                                             String dayRequest){

        User user = userRepo.findByEmail(authenticationService.getAuthInfo().getName()).get();

        return ResponseEntity.ok(activityService.getAllEventsByUserIDByDay(user.getId(), dayRequest));
    }


    //
    @DeleteMapping("/deleteEventByID")
    public ResponseEntity<ActivityResponses> deleteUserEventByEventID(@RequestParam(name = "eventID") String eventID){

        User user = userRepo.findByEmail(authenticationService.getAuthInfo().getName()).get();

        return ResponseEntity.ok(activityService.deleteEvent(user.getId(), eventID));
    }


}
