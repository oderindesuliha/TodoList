package org.todolist.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.todolist.data.models.Task;
import org.todolist.data.models.User;
import org.todolist.data.repositories.TaskRepository;
import org.todolist.data.repositories.UserRepository;
import org.todolist.dtos.requests.DeleteTaskRequest;
import org.todolist.dtos.requests.TaskRequest;
import org.todolist.dtos.requests.UpdateTaskRequest;
import org.todolist.dtos.responses.TaskResponse;
import org.todolist.exceptions.TaskExceptions;
import org.todolist.exceptions.UserExceptions;
import org.todolist.utils.TaskMapper;
import org.todolist.validations.TaskValidations;

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
        TaskValidations.validateTask(taskRequest);

        if (taskRepository.existsByUsername(taskRequest.getUsername())) {
            throw new UserExceptions("Username already exists");
        }

        Optional<User> selectedUser = userRepository.findById(taskRequest.getUserId());
        if (selectedUser.isEmpty()) {
            throw new UserExceptions("User not found");
        }
        User user = selectedUser.get();

        Task task = TaskMapper.mapTaskRequest(taskRequest);
        Task savedTask = taskRepository.save(task);

        if (user.getTaskIds() == null) {
            user.setTaskIds(new ArrayList<>());
        }
        user.getTaskIds().add(savedTask.getTaskId());
        userRepository.save(user);

        return TaskMapper.mapTaskResponse(savedTask, "Task saved successfully");
    }


    @Override
    public TaskResponse updateRequest(UpdateTaskRequest updateTaskRequest) {
        TaskValidations.validateUpdateTask(updateTaskRequest);

        Optional<User> selectedUser = userRepository.findById(updateTaskRequest.getUserId());
        if (selectedUser.isEmpty()) {
            throw new TaskExceptions("User not found");
        }


        Optional<Task> selectedTask = taskRepository.findById(updateTaskRequest.getTaskId());
        if (selectedTask.isEmpty()) {
            throw new TaskExceptions("Task not found!");
        }

        Task task = selectedTask.get();

        if (!task.getUserId().equals(updateTaskRequest.getUserId())) {
            throw new TaskExceptions("You are not authorized to update this task!");
        }


        task.setTaskTitle(updateTaskRequest.getTaskTitle());
        task.setTaskDescription(updateTaskRequest.getTaskDescription());
        task.setTaskPriority(updateTaskRequest.getTaskPriority());
        task.setTaskStatus(updateTaskRequest.getTaskStatus());
        task.setCompleted(false);
        Task updatedContact = taskRepository.save(task);

        return TaskMapper.mapTaskResponse(updatedContact, "Task updated successfully");
    }

    @Override
    public String deleteTask(DeleteTaskRequest deleteTaskRequest) {
        Optional<User> selectedUser = userRepository.findById(deleteContactRequest.getUserId());
        if (selectedUser.isEmpty()) {
            throw new TaskExceptions("User not found");
        }
        User user = selectedUser.get();

        System.out.println(deleteTaskRequest.getEmail());
        Optional<Task> selectedTask = taskRepository.findByPhoneNumber(deleteContactRequest.getPhoneNumber());
        if (selectedTask.isEmpty()) {
            throw new TaskExceptions("Task not found");
        }
        Task task = selectedTask.get();

        if (!user.getUserId().equals(task.getUserId()))
            throw new TaskExceptions("You are not authorized to delete this contact!");

        taskRepository.delete(task);
        return "Task deleted successfully";
    }
}




