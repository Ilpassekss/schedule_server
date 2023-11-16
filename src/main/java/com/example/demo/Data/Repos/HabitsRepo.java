package com.example.demo.Data.Repos;

import com.example.demo.Data.Entities.Habit;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface HabitsRepo extends MongoRepository<Habit, String> {
}
