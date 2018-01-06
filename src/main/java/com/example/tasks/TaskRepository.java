package com.example.tasks;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface TaskRepository extends MongoRepository<Task, String> {

    List<Task> findAllByName(@Param("name") String name);

    List<Task> findAllByUser(@Param("user") String user);

    List<Task> findAllByUserAndStatus(@Param("user") String user, @Param("status") String status);

    List<Task> findAllByUserAndDurationIsGreaterThanEqual(@Param("user") String user,
                                                                      @Param("duration") Long duration);

    List<Task> findAllByUserAndStatusAndCreatedDateBetween(@Param("user") String user,
                                                    @Param("status") String status,
                                                    @Param("date") Date less,
                                                    @Param("date") Date greater);

    Long deleteTaskByName(@Param("name") String name);
}
