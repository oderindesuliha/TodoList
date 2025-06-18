package org.todolist.validations;

import org.todolist.dtos.requests.TaskRequest;
import org.todolist.dtos.requests.UpdateTaskRequest;
import org.todolist.exceptions.TaskExceptions;

public class TaskValidations {
    public static void validateTask(TaskRequest taskRequest) {
        if (taskRequest.getTaskTitle() == null || taskRequest.getTaskTitle().trim().isEmpty() || !taskRequest.getTaskTitle().matches("^[A-Za-z\\s-']{2,100}$")) {
            throw new TaskExceptions("Task title cannot be empty");
        }
        if (taskRequest.getTaskDescription() == null || taskRequest.getTaskDescription().trim().isEmpty() || !taskRequest.getTaskDescription().matches("^[A-Za-z\\s-']{2,250}$")) {
            throw new TaskExceptions("Task Description is required");
        }

    }
    public static void validateUpdateTask (UpdateTaskRequest updatetaskRequest){
        if (updatetaskRequest.getTaskTitle() == null || updatetaskRequest.getTaskTitle().trim().isEmpty()) {
            throw new TaskExceptions("Task title cannot be empty");
        }
        if (updatetaskRequest.getTaskDescription() == null || updatetaskRequest.getTaskDescription().trim().isEmpty()) {
            throw new TaskExceptions("Task Description is required");
        }

    }

}
