package com.example.demo.Regsitration.Controllers;

import com.example.demo.Regsitration.Services.AuthService;
import com.example.demo.Regsitration.Entities.User;
import com.example.demo.Regsitration.Repos.UserRepo;
import com.example.demo.Regsitration.Services.AuthService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.message.Message;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class DemoController {

    private final AuthService authenticationService;

    private final UserRepo userRepo ;



    // here you have to send access token and user request message.
    // if program save response in database return true , if program catch some problems return 400`s errors or false
    @PreAuthorize("hasAnyAuthority('USER')")
    @GetMapping("/sendResponse")
    public ResponseEntity<String> sendResponse(@RequestBody String str){
        String email = authenticationService.getAuthInfo().getName();

        User user = userRepo.findByEmail(email).get();
        return ResponseEntity.ok(user.getFirstName());
    }


}
