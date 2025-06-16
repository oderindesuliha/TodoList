package org.todolist.dtos.responses;

import lombok.Data;

@Data
public class UserLoginResponse {
    private String email;
    private String userName;
    private String id;
}
