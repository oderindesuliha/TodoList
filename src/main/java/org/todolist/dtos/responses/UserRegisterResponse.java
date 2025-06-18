package org.todolist.dtos.responses;

import lombok.Data;

import java.util.List;

@Data
public class UserRegisterResponse {
    private String userId;
    private String firstName;
    private String lastName;
    private String userName;
    private String email;
    private List<String> taskIds;
    private String message;
    }
