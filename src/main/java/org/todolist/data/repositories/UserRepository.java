package org.todolist.data.repositories;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.todolist.data.models.User;

import java.util.Optional;


public interface UserRepository extends MongoRepository<User, String> {
    User findByUserName(String userName);
    boolean existsByEmail(String email);
    boolean existsByUserName(String userName);
    Optional<User> findByUsername(String username);
}
