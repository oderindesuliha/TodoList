package org.todolist.services;


import org.todolist.data.models.Task;
import org.todolist.data.models.User;

public interface TaskService {
    Task createTask(User user);
    Task updateTask(Task task);
    void deleteTask(Task task);

}
