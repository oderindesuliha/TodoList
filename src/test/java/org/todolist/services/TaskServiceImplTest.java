package org.todolist.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.todolist.data.repositories.TaskRepository;
import org.todolist.data.repositories.UserRepository;
import org.todolist.dtos.requests.*;
import org.todolist.dtos.responses.TaskResponse;
import org.todolist.dtos.responses.UserRegisterResponse;


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
        taskRequest.setTaskPriority("OPTIONAL");
        taskRequest.setTaskStatus("PENDING");
        taskRequest.setUserId(userResponse.getUserId());

        TaskResponse taskResponse = taskService.createTask(taskRequest);
        assertNotNull(taskResponse);
        assertEquals("Test Task", taskResponse.getTaskTitle());
        assertEquals("This is a test task", taskResponse.getTaskDescription());
        assertEquals("Task saved successfully", taskResponse.getMessage());

    }

    @Test
    void testToUpdateTask() {
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
        updateTaskRequest.setTaskPriority("CRITICAL");
        updateTaskRequest.setTaskStatus("IN_PROGRESS");
        updateTaskRequest.setTaskId(taskResponse.getTaskId());

        TaskResponse response = taskService.updateRequest(updateTaskRequest);
        assertNotNull(response);
        assertEquals("Task saved successfully", response.getMessage());
        assertEquals("my gym day", response.getTaskTitle());
        assertEquals("i will be at the gym today", response.getTaskDescription());
        assertEquals("CRITICAL", response.getTaskPriority());
        assertEquals("IN_PROGRESS", response.getTaskStatus());
    }


    @Test
    void testToDeleteTask() {
        UserRegisterRequest registerUser = registerUser();
        UserRegisterResponse userResponse = userService.register(registerUser);
        assertNotNull(userResponse);
        assertEquals("Account created successfully", userResponse.getMessage());

        TaskRequest taskRequest = new TaskRequest();
        taskRequest.setUserId(userResponse.getUserId());
        taskRequest.setTaskTitle("My New Task");
        taskRequest.setTaskDescription("I will take my bath");
        taskRequest.setTaskPriority("OPTIONAL");
        taskRequest.setTaskStatus("PENDING");
        TaskResponse taskResponse = taskService.createTask(taskRequest);
        assertNotNull(taskResponse);

        DeleteTaskRequest deleteTaskRequest = new DeleteTaskRequest();
        deleteTaskRequest.setUserId(userResponse.getUserId());
        deleteTaskRequest.setTaskId(taskResponse.getTaskId());
        String deleteResponse = taskService.deleteTask(deleteTaskRequest);
        assertEquals("Task deleted successfully", deleteResponse);
        assertFalse(taskRepository.existsById(taskResponse.getTaskId()), "Task should be deleted from repository");


    }
}