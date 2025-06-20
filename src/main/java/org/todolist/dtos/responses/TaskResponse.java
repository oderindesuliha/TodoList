package org.todolist.dtos.responses;

import lombok.Data;

@Data
public class TaskResponse {
    private String taskId;
    private String taskTitle;
    private String taskDescription;
    private String taskStatus;
    private String taskPriority;
    private String message;
}
