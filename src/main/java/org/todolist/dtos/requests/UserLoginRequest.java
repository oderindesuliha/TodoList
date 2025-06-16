package org.todolist.dtos.requests;

import lombok.Data;

@Data
public class UserLoginRequest {
    private String userName;
    private String password;
}
