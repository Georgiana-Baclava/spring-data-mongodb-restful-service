package com.example.tasks;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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

	private static boolean setUpIsDone = false;

	private static SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

	@Before
	public void setUp() {
		if (setUpIsDone) {
			return;
		}
		addMultipleTasks();
		setUpIsDone = true;
	}

	public void addMultipleTasks() {
		taskRepository.save(new Task("Lili", "project1", new Date(), new Date(), (long) 2, "pending"));
		taskRepository.save(new Task("Lili", "project2", new Date(), new Date(), (long) 24, "completed"));
		taskRepository.save(new Task("Lili", "project6", new Date(), new Date(),(long) 543, "pending"));
		taskRepository.save(new Task("Lili", "project7", new Date(), new Date(), (long) 32, "completed"));
		taskRepository.save(new Task("Lili", "project8", new Date(), new Date(), (long) 2, "pending"));
		taskRepository.save(new Task("Sam", "project3", new Date(), new Date(), (long) 32, "completed"));
		taskRepository.save(new Task("Cristian", "project4", new Date(), new Date(), (long) 12, "new"));
		taskRepository.save(new Task("Sam", "project5", new Date(), new Date(), (long) 65, "pending"));
	}

	@Test
	public void getAllTasksTest() {
		List<Task> expectedTasks = taskController.getAllTasks();
		List<Task> actualTasks = taskRepository.findAll();
		assertTrue(expectedTasks.equals(actualTasks));
	}

	@Test
	public void createTaskTest() throws ParseException {
		Task newTask = taskController.createTask("Lili", "project9",
				formatter.parse("12/12/2028"), formatter.parse("12/12/2038"),
				(long) 65, "pending");
		Task actualTask = taskRepository.findOne(newTask.getId());
		assertEquals(newTask, actualTask);
	}

	@Test
	public void getTasksByUserTest() {
		List<Task> expectedTasks = taskController.getTasksByUser("Sam");
		List<Task> actualTasks = taskRepository.findAllByUser("Sam");
		System.out.println(expectedTasks);
		System.out.println(actualTasks);
		assertTrue(expectedTasks.equals(actualTasks));
	}

	@Test
	public void getTasksByUserAndStatusTest() {
		List<Task> expectedTasks = taskController.getTasksByUserAndStatus("Sam", "completed");
		List<Task> actualTasks = taskRepository.findAllByUserAndStatus("Sam", "completed");
		System.out.println(expectedTasks);
		System.out.println(actualTasks);
		assertTrue(expectedTasks.equals(actualTasks));
	}

	@Test
	public void getAllTasksByUserAndDurationIsGreaterThanEqualTest() {
		List<Task> expectedTasks = taskController.getAllTasksByUserAndDurationIsGreaterThanEqual("Lili", (long) 24);
		List<Task> actualTasks = taskRepository.findAllByUserAndDurationIsGreaterThanEqual("Lili", (long) 24);
		System.out.println(expectedTasks);
		System.out.println(actualTasks);
		assertTrue(expectedTasks.equals(actualTasks));
	}

	@Test
	public void getTasksByUserStatusAndCreatedDateBetweenTest() throws ParseException {
		Date less = formatter.parse("12/12/2004");
		Date greater = formatter.parse("12/12/2018");
		List<Task> expectedTasks = taskController.getTasksByUserStatusAndDateBetween("Lili", "pending", less, greater);
		List<Task> actualTasks = taskRepository.findAllByUserAndStatusAndCreatedDateBetween("Lili", "pending", less, greater);
		System.out.println(expectedTasks);
		System.out.println(actualTasks);
		assertTrue(expectedTasks.equals(actualTasks));
	}
}
