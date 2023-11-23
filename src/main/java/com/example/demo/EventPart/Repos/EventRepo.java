package com.example.demo.EventPart.Repos;

import com.example.demo.EventPart.Entities.Event;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface EventRepo extends MongoRepository<Event, String> {

    Optional<List<Event>> findAllByDateAndUserID(LocalDateTime date, String userID);

    Optional<Event> findByUserIDAndId(String userID, String id);

    Optional<List<Event>> findAllByUserID(String userID);

    void deleteByIdAndUserID(String id, String userID);
}
