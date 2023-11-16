package com.example.demo.Regsitration.Requests;


import lombok.*;

@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RefreshJWTRequest {
    private String refreshToken;
}
