package com.example.demo.Regsitration.Controllers.Responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse extends Response{
    private final String type = "Bearer ";
    private String accessToken;
    private String refreshToken;
    private final String response = Response.OK_RESP;

}
