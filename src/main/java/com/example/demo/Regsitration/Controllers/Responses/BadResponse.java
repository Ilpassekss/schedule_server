package com.example.demo.Regsitration.Controllers.Responses;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BadResponse extends Response{
    private String response = BAD_RESP;
}
