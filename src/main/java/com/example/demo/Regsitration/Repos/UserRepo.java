package com.example.demo.Regsitration.Repos;


import com.example.demo.Regsitration.Entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepo extends MongoRepository<User, String> {
    Optional<User> findByEmail(String userEmail);

    long countByEmail(String email);
}
