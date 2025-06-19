package org.todolist.dtos.requests;

import lombok.Data;

@Data
public class TaskRequest {
    private String taskTitle;
    private String taskDescription;
    private String taskStatus;
    private String taskPriority;
    private String userId;
}
