package org.todolist.data.repositories;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.todolist.data.models.User;



public interface UserRepository extends MongoRepository<User, String> {
    User findByUserName(String userName);
    User findByEmail(String email);
    boolean existsByEmail(String email);
}
