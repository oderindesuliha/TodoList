package org.todolist.dtos.responses;

import lombok.Data;

@Data
public class TaskResponse {
    private String taskId;
    private String taskName;
    private String taskDescription;
    private String taskStatus;
    private String taskPriority;
}
