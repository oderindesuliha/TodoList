package org.todolist.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.todolist.data.models.User;
import org.todolist.data.repositories.TaskRepository;
import org.todolist.data.repositories.UserRepository;
import org.todolist.dtos.requests.*;
import org.todolist.dtos.responses.TaskResponse;
import org.todolist.dtos.responses.UserRegisterResponse;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TaskServiceImplTest {

    @Autowired
    private TaskService taskService;
    @Autowired
    private UserService userService;
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        taskRepository.deleteAll();
        userRepository.deleteAll();
    }

    private UserRegisterRequest registerUser() {
        UserRegisterRequest register = new UserRegisterRequest();
        register.setFirstName("Oluwaseun");
        register.setLastName("Afolabi");
        register.setUserName("A4");
        register.setEmail("oluwaseun.afolabi@gmail.com");
        register.setPassword("12345");
        return register;
    }

    @Test
    public void testToRegisterUserAndCreateTask() {
        UserRegisterRequest registerUser = registerUser();
        UserRegisterResponse userResponse = userService.register(registerUser);
        assertNotNull(userResponse);
        assertEquals("Account created successfully", userResponse.getMessage());

        TaskRequest taskRequest = new TaskRequest();
        taskRequest.setTaskTitle("Test Task");
        taskRequest.setTaskDescription("This is a test task");
        taskRequest.setTaskPriority("HIGH");
        taskRequest.setTaskStatus("PENDING");
        taskRequest.setUserId(userResponse.getUserId());

        TaskResponse taskResponse = taskService.createTask(taskRequest);
        assertNotNull(taskResponse);
        assertEquals("Test Task", taskResponse.getTaskTitle());
        assertEquals("This is a test task", taskResponse.getTaskDescription());
        assertEquals("Task saved successfully", taskResponse.getMessage());

    }

    @Test
    void testUpdateTask() {
        UserRegisterRequest registerUser = registerUser();
        UserRegisterResponse userResponse = userService.register(registerUser);
        assertNotNull(userResponse);
        assertEquals("Account created successfully", userResponse.getMessage());

        TaskRequest taskRequest = new TaskRequest();
        taskRequest.setUserId(userResponse.getUserId());
        taskRequest.setTaskTitle("new Task");
        taskRequest.setTaskDescription("my gym day is coming");
        taskRequest.setTaskPriority("OPTIONAL");
        taskRequest.setTaskStatus("PENDING");
        TaskResponse taskResponse = taskService.createTask(taskRequest);

        UpdateTaskRequest updateTaskRequest = new UpdateTaskRequest();
        updateTaskRequest.setUserId(userResponse.getUserId());
        updateTaskRequest.setTaskTitle("my gym day");
        updateTaskRequest.setTaskDescription("i will be at the gym today");
        updateTaskRequest.setTaskPriority("IMPORTANT");
        updateTaskRequest.setTaskStatus("IN_PROGRESS");
        updateTaskRequest.setTaskId(taskResponse.getTaskId());

        TaskResponse response = taskService.updateRequest(updateTaskRequest);
        assertNotNull(response);
        assertEquals("Task saved successfully", response.getMessage());
        assertEquals("my gym day", response.getTaskTitle());
        assertEquals("i will be at the gym today", response.getTaskDescription());
        assertEquals("IMPORTANT", response.getTaskPriority());
        assertEquals("IN_PROGRESS", response.getTaskStatus());
    }

    @Test
    void testUndoneTasks() {
        UserRegisterRequest registerUser = registerUser();
        UserRegisterResponse userResponse = userService.register(registerUser);
        assertNotNull(userResponse);
        assertEquals("Account created successfully", userResponse.getMessage());

        TaskRequest taskRequest1 = new TaskRequest();
        taskRequest1.setUserId(userResponse.getUserId());
        taskRequest1.setTaskTitle("Task 1");
        taskRequest1.setTaskDescription("Gym day");
        taskRequest1.setTaskPriority("IMPORTANT");
        taskRequest1.setTaskStatus("PENDING");
        TaskResponse response1 = taskService.createTask(taskRequest1);
        assertNotNull(response1);

        TaskRequest taskRequest2 = new TaskRequest();
        taskRequest2.setUserId(userResponse.getUserId());
        taskRequest2.setTaskTitle("Task 2");
        taskRequest2.setTaskDescription("School day");
        taskRequest2.setTaskPriority("URGENT");
        taskRequest2.setTaskStatus("PENDING");
        TaskResponse response = taskService.createTask(taskRequest2);
        assertNotNull(response);


    }
}