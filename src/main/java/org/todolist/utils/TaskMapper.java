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

    public static TaskResponse mapTaskResponse(Task taskRequest) {
        TaskResponse response = new TaskResponse();
        response.setTaskId(taskRequest.getTaskId());
        response.setTaskTitle(taskRequest.getTaskTitle());
        response.setTaskDescription(taskRequest.getTaskDescription());
        response.setTaskPriority(taskRequest.getTaskPriority());
        response.setTaskStatus(taskRequest.getTaskStatus());
        response.setMessage("Task saved successfully");

        return response;
    }
}