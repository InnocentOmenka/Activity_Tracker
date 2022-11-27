package com.example.week8.service.serviceImpl;

import com.example.week8.models.Task;
import com.example.week8.models.User;
import com.example.week8.repositories.TaskRepository;
import com.example.week8.repositories.UserRepository;
import com.example.week8.service.TaskService;
import lombok.*;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
@Data
@Getter
@Setter
//@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;


    public Task createTask(Long id, Task task){
        Optional<User> user = userRepository.findById(id);

        Task createdTask = null;
        try {
            createdTask = taskRepository.save(task);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        return createdTask;
    }



    public List<Task> getAllTasks() {

        return taskRepository.findAll(Sort.by((Sort.Direction.ASC), "id"));
    }


    public List<Task> findByStatus(String status) {

        return taskRepository.findByStatus(status);
    }



    public Task updateTask(Task taskToUpdate, Long id){
        Task oldTask = taskRepository.findById(id).orElse(null);
        if(oldTask != null){
            oldTask.setTitle(taskToUpdate.getTitle());
            oldTask.setDescription(taskToUpdate.getDescription());
            //Date object
            Date date= new Date();
            //getTime() returns current time in milliseconds
            long time = date.getTime();
            //Passed the milliseconds to constructor of Timestamp class
            Timestamp completedTime = new Timestamp(time);
            String status = taskToUpdate.getStatus();
            String completedStatus = "completed";
            if (status.equalsIgnoreCase(completedStatus)){
                oldTask.setStatus(completedStatus);
                oldTask.setCompleted_At(completedTime);
            }
            else{
                oldTask.setStatus(status);
                oldTask.setCompleted_At(null);
            }
            return taskRepository.save(oldTask);
        }
        return  taskToUpdate;
    }

    public Task getATask(Long id) {
        Optional<Task> task = taskRepository.findById(id);
        if (task.isEmpty()) {
            return null;
        }
        return task.get();
    }

    public List<Task> getTasksByStatus(String status) {
        List<Task> tasks = taskRepository.findByStatus(status);
        if (tasks.isEmpty()) {
            return null;
        }
        return tasks;
    }

    public Task editTask(Long id, Task task) {
        Task existingTask = taskRepository.findById(id).orElse(null);
        if (existingTask != null) {
            existingTask.setDescription(task.getDescription());
            existingTask.setTitle(task.getTitle());
            existingTask.setStatus(task.getStatus());
            existingTask = taskRepository.save(existingTask);
            if (existingTask.getStatus().equalsIgnoreCase("done")) {
                existingTask.setCompleted_At(task.getUpdated_At());
            }
            return existingTask;
        }
        return null;
    }

//    public void deleteATask(Long id) {
//        taskRepository.deleteById(id);
//    }


    public Task deleteATask(Long id){
        if(taskRepository.existsById(id)){
            taskRepository.deleteById(id);
        }
        return null;
    }

}
