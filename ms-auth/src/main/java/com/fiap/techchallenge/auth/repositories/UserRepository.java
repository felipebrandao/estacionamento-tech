package com.fiap.techchallenge.auth.repositories;

import com.fiap.techchallenge.auth.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends MongoRepository<User, UUID> {

    Optional<User> findByEmail(String email);

}
