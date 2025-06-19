package org.todolist.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.todolist.data.models.Task;
import org.todolist.data.models.User;
import org.todolist.data.repositories.TaskRepository;
import org.todolist.data.repositories.UserRepository;
import org.todolist.dtos.requests.*;
import org.todolist.dtos.responses.TaskResponse;
import org.todolist.exceptions.TaskExceptions;
import org.todolist.exceptions.UserExceptions;
import org.todolist.utils.TaskMapper;
import org.todolist.utils.TaskPriority;
import org.todolist.validations.TaskValidations;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public TaskResponse createTask(TaskRequest taskRequest) {
        if (!TaskValidations.validateTask(taskRequest)) {
            if (taskRequest.getTaskTitle() == null || taskRequest.getTaskTitle().trim().isEmpty() ||
                    !taskRequest.getTaskTitle().matches("^[A-Za-z\\s-']{2,100}$")) {
                throw new TaskExceptions("Task title cannot be empty or invalid");
            }
            if (taskRequest.getTaskDescription() == null || taskRequest.getTaskDescription().trim().isEmpty() ||
                    !taskRequest.getTaskDescription().matches("^[A-Za-z\\s-']{2,250}$")) {
                throw new TaskExceptions("Task Description is required or invalid");
            }
        }

        try {
            TaskPriority.valueOf(taskRequest.getTaskPriority());
        } catch (IllegalArgumentException e) {
            throw new TaskExceptions("Invalid task priority: " + taskRequest.getTaskPriority());
        }

        Optional<User> selectedUser = userRepository.findById(taskRequest.getUserId());
        if (selectedUser.isEmpty()) {
            throw new UserExceptions("User not found");
        }
        User user = selectedUser.get();

        Task task = new Task();
        task.setTaskTitle(taskRequest.getTaskTitle());
        task.setTaskDescription(taskRequest.getTaskDescription());
        task.setTaskPriority(taskRequest.getTaskPriority());
        task.setTaskStatus(taskRequest.getTaskStatus());
        task.setCreatedAt(LocalDateTime.now());
        task.setUserId(taskRequest.getUserId());

        Task savedTask = taskRepository.save(task);

        if (user.getTaskIds() == null) {
            user.setTaskIds(new ArrayList<>());
        }
        user.getTaskIds().add(savedTask.getTaskId());
        userRepository.save(user);

        return TaskMapper.mapTaskResponse(savedTask);
    }

    @Override
    public TaskResponse updateRequest(UpdateTaskRequest updateTaskRequest) {
        TaskValidations.validateUpdateTask(updateTaskRequest);

        try {
            TaskPriority.valueOf(updateTaskRequest.getTaskPriority());
        } catch (IllegalArgumentException e) {
            throw new TaskExceptions("Invalid task priority: " + updateTaskRequest.getTaskPriority());
        }

        Optional<User> selectedUser = userRepository.findById(updateTaskRequest.getUserId());
        if (selectedUser.isEmpty()) {
            throw new UserExceptions("User not found");
        }

        Optional<Task> selectedTask = taskRepository.findById(updateTaskRequest.getTaskId());
        if (selectedTask.isEmpty()) {
            throw new TaskExceptions("Task not found");
        }

        Task task = selectedTask.get();

        if (!task.getUserId().equals(updateTaskRequest.getUserId())) {
            throw new TaskExceptions("You are not authorized to update this task");
        }

        task.setTaskId(task.getTaskId());
        task.setTaskTitle(updateTaskRequest.getTaskTitle());
        task.setTaskDescription(updateTaskRequest.getTaskDescription());
        task.setTaskPriority(updateTaskRequest.getTaskPriority());
        task.setTaskStatus(updateTaskRequest.getTaskStatus());
        task.setCompleted(false);
        Task updated = taskRepository.save(task);

        return TaskMapper.mapTaskResponse(updated);
    }

    @Override
    public String deleteTask(DeleteTaskRequest deleteTaskRequest) {
        Optional<User> selectedUser = userRepository.findById(deleteTaskRequest.getUserId());
        if (selectedUser.isEmpty()) {
            throw new UserExceptions("User not found");
        }
        User user = selectedUser.get();

        Optional<Task> selectedTask = taskRepository.findById(deleteTaskRequest.getTaskId());
        if (selectedTask.isEmpty()) {
            throw new TaskExceptions("Task not found");
        }
        Task task = selectedTask.get();

        if (!user.getUserId().equals(task.getUserId())) {
            throw new TaskExceptions("You are not authorized to delete this task");
        }

        taskRepository.delete(task);
        user.getTaskIds().remove(task.getTaskId());
        userRepository.save(user);
        return "Task deleted successfully";
    }
}