package org.todolist.utils;

import org.todolist.data.models.User;
import org.todolist.dtos.requests.UserRegisterRequest;
import org.todolist.dtos.responses.UserRegisterResponse;

public class UserMapper {
    public static User mapRegisterRequest(UserRegisterRequest request) {
        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setUserName(request.getUserName());
        user.setEmail(request.getEmail());
        user.setPassword(Password.hashPassword(request.getPassword()));
        return user;
    }

    public static UserRegisterResponse mapRegisterResponse(User user, String message) {
        UserRegisterResponse response = new UserRegisterResponse();
        response.setUserId(user.getUserId());
        response.setMessage(message);
        return response;
    }
}
