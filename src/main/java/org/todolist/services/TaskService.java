package org.todolist.services;


import org.springframework.stereotype.Service;
import org.todolist.data.models.Task;
import org.todolist.data.models.User;
import org.todolist.dtos.requests.DeleteTaskRequest;
import org.todolist.dtos.requests.TaskRequest;
import org.todolist.dtos.requests.UpdateTaskRequest;
import org.todolist.dtos.responses.TaskResponse;

import java.util.Optional;

public interface TaskService {

    TaskResponse createTask(TaskRequest taskRequest);

    TaskResponse updateTask(UpdateTaskRequest updateTaskRequest);
    String deleteTask(DeleteTaskRequest deleteTaskRequest);


}
