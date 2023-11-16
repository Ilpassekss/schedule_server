package com.example.demo.Data.Entities;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@Getter
@Setter
@Document(collection = "habit_data")
public class Habit extends Event{

    private String id;
    private long frequency;

}
