package org.todolist.dtos.requests;

import lombok.Data;

@Data
public class DoneTaskRequest {
    private String taskTitle;
    private String userId;
}
