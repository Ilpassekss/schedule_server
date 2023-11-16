package com.example.demo.Regsitration.Repos;

import com.example.demo.Regsitration.Entities.RefreshToken;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RefreshTokenRepo extends MongoRepository<RefreshToken, String> {

    Optional<RefreshToken> findRefreshTokenByUserEmail(String email);

    void deleteByUserEmail(String email);
}
