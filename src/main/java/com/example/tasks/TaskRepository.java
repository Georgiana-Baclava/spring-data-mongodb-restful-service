package com.example.tasks;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaskRepository extends MongoRepository<Task, String> {

    List<Task> findAllByName(@Param("name") String name);

    List<Task> findAllByUser(@Param("user") String user);

    Long deleteTaskByName(@Param("name") String name);
}
