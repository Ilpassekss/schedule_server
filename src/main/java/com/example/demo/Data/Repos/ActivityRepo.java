package com.example.demo.Data.Repos;

import com.example.demo.Data.Entities.Activity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ActivityRepo extends MongoRepository<Activity, String> {
}
