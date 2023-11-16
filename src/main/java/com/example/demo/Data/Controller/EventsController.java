package com.example.demo.Data.Controller;


import com.example.demo.Data.Entities.Activity;
import com.example.demo.Data.Repos.ActivityRepo;
import com.example.demo.Data.Repos.HabitsRepo;
import com.example.demo.Data.Requests.ActivityRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class EventsController {

    private final ActivityRepo activityRepo;

    private final HabitsRepo habitsRepo;


    @PreAuthorize("hasAnyAuthority('USER')")
    @GetMapping("/addNewActivity")
    ResponseEntity<String> saveNewActivity(@RequestBody ActivityRequest activityRequest){
        return ResponseEntity.ok("ok");
    }


}
