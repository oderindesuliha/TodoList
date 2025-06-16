package org.todolist.data.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.todolist.data.models.Task;
//import org.todolist.utils.Priority;


import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TaskRepositoryTest {
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;


    @BeforeEach
    void setUp() {
        taskRepository.deleteAll();
        userRepository.deleteAll();

    }

    @Test
    public void testToCreateTask() {
        Task task = new Task();
        task.setTaskTitle("Test Task");
        task.setTaskDescription("I am having a test today");
        task.setTaskStatus("pending");
        Task savedTask = taskRepository.save(task);
        assertEquals(1, savedTask);
        assertNotNull(savedTask.getTaskId());

        assertEquals("Test Task", savedTask.getTaskTitle());
        assertEquals("I am having a test today", savedTask.getTaskDescription());
        assertEquals("pending", savedTask.getTaskStatus());
    }


}