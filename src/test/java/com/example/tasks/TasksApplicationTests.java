package com.example.tasks;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TasksApplicationTests {

	@Autowired
	private TaskController taskController;

	@Autowired
	private TaskRepository taskRepository;

	@Test
	public void contextLoads() {
	}

	@Test
	public void getAllTasksTest() {
		List<Task> tasks = taskController.getAllTasks();
		assertTrue(tasks.isEmpty());
	}

	@Test
	public void createTaskTest() {
		Task newTask = taskController.createTask("Lili", "project1", new Date(), "pending");
		Task actualTask = taskRepository.findOne(newTask.getId());
		assertEquals(newTask, actualTask);
	}
}
