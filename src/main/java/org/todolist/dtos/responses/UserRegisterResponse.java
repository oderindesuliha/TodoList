package org.todolist.dtos.responses;

import lombok.Data;

@Data
public class UserRegisterResponse {
    private String userName;
    private String email;
    private String id;
}
