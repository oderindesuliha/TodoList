package org.todolist.utils;

import org.todolist.data.models.Task;
import org.todolist.dtos.requests.TaskRequest;
import org.todolist.dtos.responses.TaskResponse;

public class TaskMapper {
    public static Task mapTaskRequest(TaskRequest request) {
        Task task = new Task();
        task.setTaskTitle(request.getTaskTitle());
        task.setTaskDescription(request.getTaskDescription());
        task.setTaskPriority(request.getTaskPriority());
        task.setTaskStatus(request.getTaskStatus());
        task.setCompleted(false);
        return task;
    }

    public static TaskResponse mapTaskResponse(Task task, String message) {
        TaskResponse response = new TaskResponse();
        response.setTaskTitle(response.getTaskTitle());
        response.setTaskDescription(response.getTaskDescription());
        response.setTaskPriority(response.getTaskPriority());
        response.setTaskStatus(response.getTaskStatus());
        response.setMessage(message);
        return response;
    }
}