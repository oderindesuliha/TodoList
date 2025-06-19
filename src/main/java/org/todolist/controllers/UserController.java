package org.todolist.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.todolist.dtos.requests.UserLoginRequest;
import org.todolist.dtos.requests.UserLogoutRequest;
import org.todolist.dtos.requests.UserRegisterRequest;
import org.todolist.dtos.responses.ApiResponse;
import org.todolist.dtos.responses.UserLoginResponse;
import org.todolist.dtos.responses.UserLogoutResponse;
import org.todolist.dtos.responses.UserRegisterResponse;
import org.todolist.exceptions.UserExceptions;
import org.todolist.services.UserService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

        @Autowired
        private UserService userService;

        @PostMapping("/register")
        public ResponseEntity<?> register(@RequestBody UserRegisterRequest request) {
            try {
                UserRegisterResponse response = userService.register(request);
                return new ResponseEntity<>(new ApiResponse(true, response), HttpStatus.CREATED);
            } catch (UserExceptions error) {
                return new ResponseEntity<>(error.getMessage(), HttpStatus.BAD_REQUEST);
            } catch (Exception error) {
                return new ResponseEntity<>(new ApiResponse(false, error.getMessage()), HttpStatus.BAD_REQUEST);
            }
        }

        @PostMapping("/login")
        public ResponseEntity<?> login(@RequestBody UserLoginRequest request) {
            try {
                UserLoginResponse response = userService.login(request);
                return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
            } catch (UserExceptions error) {
                Map<String, String> errorResponse = new HashMap<>();
                errorResponse.put("message", error.getMessage());
                return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
            } catch (Exception error) {
                Map<String, String> errorResponse = new HashMap<>();
                errorResponse.put("message", "An unexpected error occurred. Please try again.");
                return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
            }
        }


        @PostMapping("/logout")
        public ResponseEntity<?> logout(@RequestBody UserLogoutRequest request) {
            try{
                UserLogoutResponse response = userService.logout(request);
                return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
            }catch (UserExceptions error){
                return new ResponseEntity<>(error.getMessage(), HttpStatus.BAD_REQUEST);
            }catch (Exception error){
                return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
            }

        }
}
