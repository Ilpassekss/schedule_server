package com.example.demo.Regsitration.Entities;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "users_token")
public class RefreshToken {


    private String id;
    @Indexed(unique = true)
    private String userEmail;
    //refresh token
    private String refreshToken;

}
