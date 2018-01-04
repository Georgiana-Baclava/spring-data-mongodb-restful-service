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
    public Task createTask(@RequestParam("name") String name,
                           @RequestParam(value = "date", required = false) Date date,
                           @RequestParam(value = "status", defaultValue = "pending") String status)
    {
        Task newTask = new Task(name, date, status);
        taskRepository.save(newTask);
        return newTask;
    }

    @RequestMapping("tasks")
    public List<Task> getTask(@RequestParam(value = "name", required = false) String name) {
        return (name == null ? taskRepository.findAll() : taskRepository.findByName(name));
    }

    @RequestMapping(value = "task", method = RequestMethod.DELETE)
    public Long deleteTask(@RequestParam("name") String name) {
        return taskRepository.deleteTaskByName(name);
    }
}
