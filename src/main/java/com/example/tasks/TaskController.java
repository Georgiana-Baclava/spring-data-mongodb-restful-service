package com.example.tasks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @RequestMapping(value = "task", method = RequestMethod.POST)
    public Task createTask(@RequestParam("user") String user,
                           @RequestParam("name") String name,
                           @RequestParam(value = "createdDate", required = false) Long createdDate,
                           @RequestParam(value = "endDate", required = false) Long endDate,
                           @RequestParam(value = "duration", required = false) Long duration,
                           @RequestParam(value = "status", defaultValue = "pending") String status)
    {
        Task newTask = new Task(user, name, createdDate, endDate, duration, status);
        taskRepository.save(newTask);
        return newTask;
    }

    @RequestMapping("tasks")
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @RequestMapping("tasksByName")
    public List<Task> getTasksByName(@RequestParam("name") String name) {
        return taskRepository.findAllByName(name);
    }

    @RequestMapping("tasksByUser")
    public List<Task> getTasksByUser(@RequestParam("user") String user) {
        return taskRepository.findAllByUser(user);
    }

    @RequestMapping("tasksByUserAndGreaterDuration")
    public List<Task> getAllTasksByUserAndDurationIsGreaterThanEqual(@RequestParam("user") String user,
                                                       @RequestParam("duration") Long duration) {
        return taskRepository.findAllByUserAndDurationIsGreaterThanEqual(user, duration);
    }

    @RequestMapping("tasksByUserStatusAndCreatedDate")
    public List<Task> getTasksByUserStatusAndCreatedDateBetween(@RequestParam("user") String user,
                                                  @RequestParam("status") String status,
                                                  @RequestParam("lessDate") Long less,
                                                  @RequestParam("greaterDate") Long greater) {
        Date lessDate = (less == null ? new Date(System.currentTimeMillis()) : new Date(less));
        Date greaterDate = (greater == null ? new Date(System.currentTimeMillis()) : new Date(greater));
        return taskRepository.findAllByUserAndStatusAndCreatedDateBetween(user, status, lessDate, greaterDate);
    }

    @RequestMapping("tasksByUserAndStatus")
    public List<Task> getTasksByUserAndStatus(@RequestParam("user") String user,
                                              @RequestParam("status") String status) {
        return taskRepository.findAllByUserAndStatus(user, status);
    }

    @RequestMapping(value = "task", method = RequestMethod.DELETE)
    public Long deleteTask(@RequestParam("name") String name) {
        return taskRepository.deleteTaskByName(name);
    }
}
