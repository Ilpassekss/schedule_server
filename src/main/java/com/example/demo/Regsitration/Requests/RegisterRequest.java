package com.example.demo.Regsitration.Requests;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RegisterRequest {
    private String firstName;
    private String secondName;
    private String email;
    private String password;
    private String country;
}
