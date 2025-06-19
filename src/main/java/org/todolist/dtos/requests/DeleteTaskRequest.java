package org.todolist.dtos.requests;


import lombok.Data;

@Data
public class DeleteTaskRequest {

    private boolean email;
    private String userId;
    private String username;
    private String taskId;


}