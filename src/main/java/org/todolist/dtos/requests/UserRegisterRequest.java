package org.todolist.dtos.requests;

import lombok.Data;

@Data
public class UserRegisterRequest {
    private String firstName;
    private String lastName;
    private String UserName;
    private String email;
    private String password;


}
