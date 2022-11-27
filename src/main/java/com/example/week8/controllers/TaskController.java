package com.example.week8.controllers;

import com.example.week8.models.Task;
import com.example.week8.service.serviceImpl.TaskServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RequestMapping("/tasks")
@RestController
@AllArgsConstructor
public class TaskController {
    private final TaskServiceImpl taskService;


    @PostMapping("/{id}/create")
    public MyResponse<Task> createTask(@PathVariable Long id, @RequestBody Task task, HttpServletResponse response){
        Task t = taskService.createTask(id, task);
        HttpStatus statusCode = HttpStatus.CREATED;
        String message = "Todo created successfully";
        if(t == null){
            statusCode = HttpStatus.BAD_REQUEST;
            message = "bad request!!!";
        }
        response.setStatus(statusCode.value());
        return new MyResponse<>(statusCode, message, t);
    }



    @RequestMapping(value = "/", method = RequestMethod.GET)
    public MyResponse<List<Task>> getAllTasks(HttpServletResponse response){
        List<Task> allTask = taskService.getAllTasks();
        HttpStatus statusCode = HttpStatus.CREATED;
        String message = "All tasks has been retrieved successfully";
        if(allTask.isEmpty()){
            statusCode = HttpStatus.BAD_REQUEST;
            message = "task does not exist";
        }
        response.setStatus(statusCode.value());
        return  new MyResponse<>(statusCode, message, allTask);
    }



    @GetMapping
    @RequestMapping("{id}")
    public  MyResponse<Task> getOneTask(@PathVariable Long id, HttpServletResponse response){
        Task retrievedTask = taskService.getATask(id);
        HttpStatus statusCode = HttpStatus.CREATED;
        String message = "Task gotten successfully";
        if(retrievedTask == null){
            statusCode = HttpStatus.BAD_REQUEST;
            message = "Task does not exist";
        }
        response.setStatus(statusCode.value());
        return  new MyResponse<>(statusCode, message, retrievedTask);
    }



    @GetMapping
    @RequestMapping("/Status/{status}")
    public MyResponse<List<Task>> findByStatus(@PathVariable String status, HttpServletResponse response){
        List<Task> taskFoundByStatus = taskService.findByStatus(status);
        HttpStatus statusCode = HttpStatus.CREATED;
        String message = "All " + status + " tasks has been retrieved successfully";
        if(taskFoundByStatus.isEmpty()){
            statusCode = HttpStatus.BAD_REQUEST;
            message = status + " task does not exist";
        }
        response.setStatus(statusCode.value());
        return  new MyResponse<>(statusCode, message, taskFoundByStatus);
    }


    @RequestMapping(path="{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public MyResponse<Task> updateTask(@RequestBody Task task, @PathVariable Long id, HttpServletResponse response ){
        Task updatedTodo = taskService.updateTask(task, id);
        HttpStatus statusCode = HttpStatus.CREATED;
        String message = "Task updated successfully";
        if(updatedTodo == null){
            statusCode = HttpStatus.BAD_REQUEST;
            message = "Task does not exist";
        }
        response.setStatus(statusCode.value());
        return  new MyResponse<>(statusCode, message, updatedTodo);
    }


    @RequestMapping(path="{id}", method = RequestMethod.DELETE)
    public MyResponse<Long> deleteTask(@PathVariable(name = "id") Long id, HttpServletResponse response){
        Long deletedTaskId = taskService.deleteATask(id).getId();
        HttpStatus statusCode = HttpStatus.OK;
        String message = "Task Deleted Successfully";
        if(deletedTaskId == null){
            statusCode = HttpStatus.BAD_REQUEST;
            message = "Task does not exist";
        }
        response.setStatus(statusCode.value());
        return  new MyResponse<>(statusCode, message, deletedTaskId);
    }



    @RequestMapping(value = "/edit/task/{id}", method = RequestMethod.POST)
    public String editTask(@PathVariable Long id, Task task) {
        taskService.editTask(id, task);
        return "redirect:/tasks";
    }

}
