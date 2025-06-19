package org.todolist.validations;

import org.todolist.data.models.User;
import org.todolist.data.repositories.UserRepository;
import org.todolist.dtos.requests.TaskRequest;
import org.todolist.dtos.requests.UpdateTaskRequest;
import org.todolist.exceptions.TaskExceptions;
import org.todolist.exceptions.UserExceptions;

import java.util.Optional;

public class TaskValidations {
    public static void validateTask(TaskRequest taskRequest) {
        boolean taskTitleIsNotEmpty =
                taskRequest.getTaskTitle().matches("^[A-Za-z\\s-']{2,100}$");
        if (taskTitleIsNotEmpty) throw new TaskExceptions("Task title cannot be empty");

        boolean taskDescriptionIsNotEmpty = taskRequest.getTaskDescription() == null ||
                taskRequest.getTaskDescription().trim().isEmpty() ||
                !taskRequest.getTaskDescription().matches("^[A-Za-z\\s-']{2,250}$");
        if (taskDescriptionIsNotEmpty) {
            throw new TaskExceptions("Task Description is required");
        }

    }

    public static void validateUpdateTask(UpdateTaskRequest updatetaskRequest) {
        if (updatetaskRequest.getTaskTitle() == null || updatetaskRequest.getTaskTitle().trim().isEmpty()) {
            throw new TaskExceptions("Task title cannot be empty");
        }
        if (updatetaskRequest.getTaskDescription() == null || updatetaskRequest.getTaskDescription().trim().isEmpty()) {
            throw new TaskExceptions("Task Description is required");
        }

    }


}
