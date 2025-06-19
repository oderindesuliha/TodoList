package org.todolist.services;


import org.todolist.dtos.requests.*;
import org.todolist.dtos.responses.TaskResponse;

import java.util.List;

public interface TaskService {

    TaskResponse createTask(TaskRequest taskRequest);

    TaskResponse updateRequest(UpdateTaskRequest updateTaskRequest);

    String deleteTask(DeleteTaskRequest deleteTaskRequest);
}


