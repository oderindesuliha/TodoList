package org.todolist.data.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.todolist.data.models.Task;
import org.todolist.data.models.User;
import org.todolist.utils.TaskPriority;
//import org.todolist.utils.Priority;


import java.util.List;
import java.util.Optional;

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
        task.setTaskPriority(TaskPriority.OPTIONAL.toString());
        Task savedTask = taskRepository.save(task);
        assertEquals(1, taskRepository.count());
        assertNotNull(savedTask.getTaskId());
    }

    @Test
    public void testToFindTaskById() {
        Task task = new Task();
        task.setTaskTitle("Test Task");
        task.setTaskDescription("I am having a test today");
        task.setTaskStatus("pending");
        task.setTaskPriority(TaskPriority.OPTIONAL.toString());
        Task savedTask = taskRepository.save(task);
        Optional<Task> foundTask = taskRepository.findById(savedTask.getTaskId());
        assertTrue(foundTask.isPresent());
        assertEquals(savedTask.getTaskId(), foundTask.get().getTaskId());
    }

    @Test
    public void testToFindTaskByTitle() {
        Task task = new Task();
        task.setTaskTitle("Test Task");
        task.setTaskDescription("I am having a test today");
        task.setTaskStatus("pending");
        task.setTaskPriority(TaskPriority.OPTIONAL.toString());
        Task savedTask = taskRepository.save(task);
        Optional<Task> foundTask = taskRepository.findByTaskTitle(savedTask.getTaskTitle());
        assertTrue(foundTask.isPresent());
    }

    @Test
    public void testToFindTaskByPriority() {
        Task task = new Task();
        task.setTaskTitle("Test Task");
        task.setTaskDescription("I am having a test today");
        task.setTaskStatus("pending");
        task.setTaskPriority(TaskPriority.OPTIONAL.toString());
        Task savedTask = taskRepository.save(task);
        Optional<Task> foundTask = taskRepository.findTasksBy(TaskPriority.OPTIONAL.toString());
        assertTrue(foundTask.isPresent());
    }

    @Test
 public void testToSaveTask_findByPriority() {
        User user = new User();
        user.setUserName("Peejay");
        user.setEmail("peejay.07@gmail.com");
        user.setPassword("<PASSWORD>");
        userRepository.save(user);

        Task task = new Task();
        task.setTaskTitle("Test Task");
        task.setTaskDescription("I am having a test today");
        task.setTaskStatus("pending");
        task.setTaskPriority(TaskPriority.OPTIONAL.toString());
        task.setUserId(user.getUserId());
        taskRepository.save(task);

        Task task1 = new Task();
        task1.setTaskTitle("A new Task");
        task1.setTaskDescription("A new Task Description");
        task1.setTaskStatus("finished");
        task1.setTaskPriority(TaskPriority.URGENT.toString());
        task1.setUserId(user.getUserId());
        taskRepository.save(task1);

    }
    }
