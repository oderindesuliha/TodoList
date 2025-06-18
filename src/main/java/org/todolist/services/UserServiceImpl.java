package org.todolist.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.todolist.data.models.User;
import org.todolist.data.repositories.UserRepository;
import org.todolist.dtos.requests.UserLoginRequest;
import org.todolist.dtos.requests.UserLogoutRequest;
import org.todolist.dtos.requests.UserRegisterRequest;
import org.todolist.dtos.responses.UserLogoutResponse;
import org.todolist.dtos.responses.UserLoginResponse;
import org.todolist.dtos.responses.UserRegisterResponse;
import org.todolist.exceptions.UserExceptions;
import org.todolist.utils.Password;
import org.todolist.utils.UserMapper;
import org.todolist.validations.UserValidations;

import java.util.ArrayList;
import java.util.Optional;


@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserRegisterResponse register(UserRegisterRequest request) {
            UserValidations.validateUser(request);
            if (userRepository.existsByEmail(request.getEmail())) {
                throw new UserExceptions("Email already exists");
            }
            if (userRepository.existsByUserName(request.getUserName())){
                throw new UserExceptions("Username already exists");
            }
            User user = UserMapper.mapRegisterRequest(request);
            user.setTaskIds(new ArrayList<>());
            User savedUser = userRepository.save(user);

            return UserMapper.mapRegisterResponse(savedUser, "Account created successfully");
        }

    @Override
    public UserLoginResponse login(UserLoginRequest request) {
        if (request.getUserName() == null || request.getUserName().isEmpty()) {
            throw new UserExceptions("Username is required");
        }
        if (request.getPassword() == null || request.getPassword().isEmpty()) {
            throw new UserExceptions("Password is required");
        }

        User user = userRepository.findByUserName(request.getUserName());
        if (user == null) {
            throw new UserExceptions("Invalid Username or Password");
        }

        if (!Password.checkPassword(request.getPassword(), user.getPassword()))
            throw new UserExceptions("Invalid Username or Password");

        UserLoginResponse response = new UserLoginResponse();
        response.setMessage(request.getUserName() + " has successfully logged in");

        return response;
    }

    public UserLogoutResponse logout(UserLogoutRequest request){
            if (request.getUsername() == null || request.getUsername().isEmpty()) {
                throw new UserExceptions("Username is required");
            }
            if (request.getPassword() == null || request.getPassword().isEmpty()) {
                throw new UserExceptions("Password is required");
            }

            User user = userRepository.findByUserName(request.getUsername());
            if (user == null) {
                throw new UserExceptions("User not found");
            }

            if (!Password.checkPassword(request.getPassword(), user.getPassword())) {
                throw new UserExceptions("Invalid password");
            }


            UserLogoutResponse response = new UserLogoutResponse();
            response.setMessage("You have successfully logged out");
            return response;
        }

}


