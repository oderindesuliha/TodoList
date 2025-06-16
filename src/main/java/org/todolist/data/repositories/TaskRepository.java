package org.todolist.data.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.todolist.data.models.Task;

import java.util.Optional;

public interface TaskRepository extends MongoRepository<Task, String> {
    Optional<Task> findById(String id);
    Task save(Task task);
    boolean existsById(String id);
    Optional<Task> findByTitle(String title);


}
