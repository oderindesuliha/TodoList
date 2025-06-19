package org.todolist.validations;


import org.todolist.dtos.requests.TaskRequest;
import org.todolist.dtos.requests.UpdateTaskRequest;
import org.todolist.exceptions.TaskExceptions;


public class TaskValidations {
    public static boolean validateTask(TaskRequest taskRequest) {
        boolean taskTitleIsNotEmpty = taskRequest.getTaskTitle() == null ||
                taskRequest.getTaskTitle().trim().isEmpty() ||
                !taskRequest.getTaskTitle().matches("^[A-Za-z0-9\\s-']{2,100}$");
        if (taskTitleIsNotEmpty) {
            return false;
        }

        boolean taskDescriptionIsNotEmpty = taskRequest.getTaskDescription() == null ||
                taskRequest.getTaskDescription().trim().isEmpty() ||
                !taskRequest.getTaskDescription().matches("^[A-Za-z0-9\\s-']{2,250}$");
        if (taskDescriptionIsNotEmpty) {
            return false;
        }

        return true;
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