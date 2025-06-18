package org.todolist.services;

import org.springframework.stereotype.Service;
import org.todolist.dtos.requests.UserLoginRequest;
import org.todolist.dtos.requests.UserLogoutRequest;
import org.todolist.dtos.requests.UserRegisterRequest;
import org.todolist.dtos.responses.UserLoginResponse;
import org.todolist.dtos.responses.UserLogoutResponse;
import org.todolist.dtos.responses.UserRegisterResponse;

public interface UserService {
    UserRegisterResponse register(UserRegisterRequest request);
    UserLoginResponse login(UserLoginRequest loginRequest);
    UserLogoutResponse logout(UserLogoutRequest request);

}
