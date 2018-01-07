package com.example.tasks;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class TasksApplicationTests {

	@Autowired
	private WebApplicationContext wac;

	@Autowired
	private TaskRepository taskRepository;

	private MockMvc mockMvc;

	private ObjectMapper mapper;

	private static boolean setUpIsDone = false;


	@Before
	public void setUp() {
		if (setUpIsDone) {
			return;
		}

		this.mockMvc = webAppContextSetup(this.wac).build();
		this.mapper = new ObjectMapper();
		addMultipleTasks();
		setUpIsDone = true;
	}

	public void addMultipleTasks() {
		taskRepository.save(new Task("Lili", "project1", System.currentTimeMillis(), System.currentTimeMillis(), (long) 2, "pending"));
		taskRepository.save(new Task("Lili", "project2", System.currentTimeMillis(), System.currentTimeMillis(), (long) 24, "completed"));
		taskRepository.save(new Task("Lili", "project6", System.currentTimeMillis(), System.currentTimeMillis(),(long) 543, "pending"));
		taskRepository.save(new Task("Lili", "project7", System.currentTimeMillis(), System.currentTimeMillis(), (long) 32, "completed"));
		taskRepository.save(new Task("Lili", "project8", System.currentTimeMillis(), System.currentTimeMillis(), (long) 2, "pending"));
		taskRepository.save(new Task("Sam", "project3", System.currentTimeMillis(), System.currentTimeMillis(), (long) 32, "completed"));
		taskRepository.save(new Task("Cristian", "project4", System.currentTimeMillis(), System.currentTimeMillis(), (long) 12, "new"));
		taskRepository.save(new Task("Sam", "project5", System.currentTimeMillis(), System.currentTimeMillis(), (long) 65, "pending"));
	}

	@Test
	public void getAllTasksTest() throws Exception {
		MvcResult result = this.mockMvc.perform(get("/tasks"))
				.andExpect(status().isOk())
				.andReturn();

		List<Task> expectedTasks = mapper.readValue(result.getResponse().getContentAsString(),
				new TypeReference<List<Task>>() {});
		List<Task> actualTasks = taskRepository.findAll();
		System.out.println(expectedTasks);
		System.out.println(actualTasks);
		assertTrue(expectedTasks.equals(actualTasks));
	}

	@Test
	public void createTaskTest() throws Exception {
		MvcResult result = this.mockMvc.perform(post("/task?user=Lili&name=project9&" +
				"duration=65&status=pending"))
				.andExpect(status().isOk())
				.andReturn();

		Task newTask = mapper.readValue(result.getResponse().getContentAsString(), Task.class);
		Task actualTask = taskRepository.findOne(newTask.getId());
		assertEquals(newTask, actualTask);
	}

	@Test
	public void getTasksByUserTest() throws Exception {
		MvcResult result = this.mockMvc.perform(get("/tasksByUser")
				.param("user", "Sam"))
				.andExpect(status().isOk())
				.andReturn();

		List<Task> expectedTasks = mapper.readValue(result.getResponse().getContentAsString(), new TypeReference<List<Task>>() {});
		List<Task> actualTasks = taskRepository.findAllByUser("Sam");
		System.out.println(expectedTasks);
		System.out.println(actualTasks);
		assertTrue(expectedTasks.equals(actualTasks));
	}

	@Test
	public void getTasksByUserAndStatusTest() throws Exception {
		MvcResult result = this.mockMvc.perform(get("/tasksByUserAndStatus")
				.param("user", "Sam").param("status", "completed"))
				.andExpect(status().isOk())
				.andReturn();
		List<Task> expectedTasks = mapper.readValue(result.getResponse().getContentAsString(), new TypeReference<List<Task>>() {});
		List<Task> actualTasks = taskRepository.findAllByUserAndStatus("Sam", "completed");
		System.out.println(expectedTasks);
		System.out.println(actualTasks);
		assertTrue(expectedTasks.equals(actualTasks));
	}

	@Test
	public void getAllTasksByUserAndDurationIsGreaterThanEqualTest() throws Exception {
		MvcResult result = this.mockMvc.perform(get("/tasksByUserAndGreaterDuration")
				.param("user", "Lili").param("duration", "24"))
				.andExpect(status().isOk())
				.andReturn();
		List<Task> expectedTasks = mapper.readValue(result.getResponse().getContentAsString(), new TypeReference<List<Task>>() {});
		List<Task> actualTasks = taskRepository.findAllByUserAndDurationIsGreaterThanEqual("Lili", (long) 24);
		System.out.println(expectedTasks);
		System.out.println(actualTasks);
		assertTrue(expectedTasks.equals(actualTasks));
	}

	@Test
	public void getTasksByUserStatusAndCreatedDateBetweenTest() throws Exception {
		Date less = new Date(Long.parseLong("1515353559825"));
		Date greater = new Date(Long.parseLong("1535354290763"));
		MvcResult result = this.mockMvc.perform(get("/tasksByUserStatusAndCreatedDate")
				.param("user", "Lili").param("status", "pending")
				.param("lessDate", "1515353559825").param("greaterDate", "1535354290763"))
				.andExpect(status().isOk())
				.andReturn();
		List<Task> expectedTasks = mapper.readValue(result.getResponse().getContentAsString(), new TypeReference<List<Task>>() {});
		List<Task> actualTasks = taskRepository.findAllByUserAndStatusAndCreatedDateBetween("Lili", "pending", less, greater);
		System.out.println(expectedTasks);
		System.out.println(actualTasks);
		assertTrue(expectedTasks.equals(actualTasks));
	}
}
