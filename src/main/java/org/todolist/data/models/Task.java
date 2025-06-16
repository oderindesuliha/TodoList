package org.todolist.data.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection ="tasks")
public class Task {
    @Id
    private String taskId;
    private String taskTitle;
    private String taskDescription;
    private String taskStatus;
    private String taskPriority;
    private String userId;

}
