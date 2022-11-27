package com.example.week8.service;

import com.example.week8.models.Task;

public interface TaskService {
    Task createTask(Long id, Task task);
    Task updateTask(Task taskToUpdate, Long id);
    Task deleteATask(Long id);
}
