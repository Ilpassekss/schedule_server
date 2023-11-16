package com.example.demo.Data.Entities;


import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "activity_data")
public class Activity extends Event{

    private String id;

}
