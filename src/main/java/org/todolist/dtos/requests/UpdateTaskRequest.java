package org.todolist.dtos.requests;

import lombok.Data;

@Data
public class UpdateTaskRequest {
    private String userId;
    private String taskTitle;
    private String taskDescription;
    private String taskStatus;
    private String taskPriority;
    public String taskId;

}
