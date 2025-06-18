package org.todolist.dtos.responses;

import lombok.Data;

@Data
public class  UserLoginResponse {
    private String id;
    private String userName;
    private String message;
}
