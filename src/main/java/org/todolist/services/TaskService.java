package org.todolist.services;


import org.todolist.dtos.UndoneTaskRequest;
import org.todolist.dtos.requests.*;
import org.todolist.dtos.responses.TaskResponse;

import java.util.List;

public interface TaskService {

    TaskResponse createTask(TaskRequest taskRequest);

    TaskResponse updateTask(UpdateTaskRequest updateTaskRequest);

    TaskResponse updateRequest(UpdateTaskRequest updateTaskRequest);

    String deleteTask(DeleteTaskRequest deleteTaskRequest);


    List<TaskResponse> undoneTask(UndoneTaskRequest undoneTaskrequest);

    List<TaskResponse> viewCompletedTasks(String username);

    List<TaskResponse> completedTasks(CompletedTaskRequest completedTaskRequest);

    void markTaskDone(TaskRequest taskRequest);

    void doneTask(DoneTaskRequest doneTaskRequest);
}
