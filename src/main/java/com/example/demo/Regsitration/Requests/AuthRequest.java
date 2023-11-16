package com.example.demo.Regsitration.Requests;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AuthRequest {
    private String email;
    private String password;
}
