package org.todolist.dtos.requests;

import lombok.Data;

@Data
public class UserLogoutRequest {
    private String username;
    private String password;

}
