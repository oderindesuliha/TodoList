package org.todolist.data.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.todolist.data.models.Task;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends MongoRepository<Task, String> {
    Optional<Task> findById(String id);
    boolean existsById(String id);
    Optional<Task> findTasksBy(String string);
    Optional<Task> findByTaskTitle(String taskTitle);

    List<Task> findTasksByUserId(String userId);
}
