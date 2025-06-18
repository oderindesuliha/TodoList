package org.todolist.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.todolist.data.repositories.UserRepository;
import org.todolist.dtos.requests.UserLoginRequest;
import org.todolist.dtos.requests.UserLogoutRequest;
import org.todolist.dtos.requests.UserRegisterRequest;
import org.todolist.dtos.responses.UserLoginResponse;
import org.todolist.dtos.responses.UserLogoutResponse;
import org.todolist.dtos.responses.UserRegisterResponse;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceImplTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;


    @BeforeEach
    void setUp() {
        userRepository.deleteAll();

    }

    @Test
    public void testToRegisterUser(){
        UserRegisterRequest request = new UserRegisterRequest();
        request.setFirstName("Oluwaseun");
        request.setLastName("Afolabi");
        request.setUserName("A4");
        request.setEmail("oluwaseun.afolabi@gmail.com");
        request.setPassword("<PASSWORD>");

        UserRegisterResponse response = userService.register(request);
        assertNotNull(response);
        assertNotNull(response.getUserId());
        assertEquals("Account created successfully", response.getMessage());

    }

    @Test
    public void testToLoginUser(){
        UserRegisterRequest registerRequest = new UserRegisterRequest();
        registerRequest.setFirstName("Chukwudi");
        registerRequest.setLastName("Okonkwo");
        registerRequest.setUserName("dikwo");
        registerRequest.setEmail("chukwudi.okonkwo@yahoo.com");
        registerRequest.setPassword("1234");
        userService.register(registerRequest);

        UserLoginRequest loginRequest = new UserLoginRequest();
        loginRequest.setUserName("dikwo");
        loginRequest.setPassword("1234");
        UserLoginResponse response = userService.login(loginRequest);
        assertNotNull(response);
        assertEquals(registerRequest.getUserName() + " has successfully logged in", response.getMessage());
    }


    @Test
    public void testToLogoutUser(){
        UserRegisterRequest registerRequest = new UserRegisterRequest();
        registerRequest.setFirstName("Chukwudi");
        registerRequest.setLastName("Beelo");
        registerRequest.setUserName("dinwo");
        registerRequest.setEmail("chukwudi.okonkwo@yahoo.com");
        registerRequest.setPassword("1234");
        userService.register(registerRequest);

        UserLoginRequest loginRequest = new UserLoginRequest();
        loginRequest.setUserName("dinwo");
        loginRequest.setPassword("1234");
        userService.login(loginRequest);

        UserLogoutRequest request = new UserLogoutRequest();
        request.setUsername("dinwo");
        request.setPassword("1234");
        UserLogoutResponse logoutResponse = userService.logout(request);

        assertNotNull(logoutResponse);
        assertNotNull(logoutResponse.getMessage());
        assertEquals("You have successfully logged out", logoutResponse.getMessage());
    }

    }


