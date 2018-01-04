package com.example.tasks;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaskRepository extends MongoRepository<Task, String> {

    List<Task> findByName(@Param("name") String name);

    Long deleteTaskByName(@Param("name") String name);
}
