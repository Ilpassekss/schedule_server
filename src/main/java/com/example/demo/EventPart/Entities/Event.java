package com.example.demo.EventPart.Entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "event_collection")
public class Event extends Activity {

    @Id
    private String id;

    private String userID;

    private LocalDateTime date;

    private LocalDateTime startTime;
    private LocalDateTime finishTime;

    private String title;
    private String description;

    //private final int POINTS = 5;

    private boolean isActive;

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean getActive(){
        return isActive;
    }
}
