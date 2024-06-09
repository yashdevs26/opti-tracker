package com.yashdevs.optiTracker.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.yashdevs.optiTracker.entity.TaskInfo;
import com.yashdevs.optiTracker.service.TaskService;

import jakarta.validation.Valid;

@RestController
public class TaskController {

	private TaskService service;

	public TaskController(TaskService service) {
		this.service = service;
	}

	@GetMapping("/users/tasks") // working fine
	public List<TaskInfo> retrieveAllTasks() {
		return service.retrieveAllTasks();
	}

	@GetMapping("/users/tasks/{taskId}") // working fine
	public TaskInfo retrieveTaskById(@Valid @PathVariable Long taskId) {
		return service.retrieveTaskById(taskId);
	}
	
	@GetMapping(value = "/users/tasks/", params = {"datetime", "userId"})
	public List<TaskInfo> retrieveTaskByDateForUser(@RequestParam String datetime , @Valid @RequestParam Long userId) {
		return service.retrieveTaskByDateForUser(datetime, userId);
	}
	
	@GetMapping(value = "/users/{userId}/tasks") // working fine
	public List<TaskInfo> retrieveAllTasksForUser(@Valid @PathVariable Long userId) {
		return service.retrieveAllTasksForUser(userId);
	}
	
	@GetMapping(value = "/users/tasks/", params = {"status", "userId"}) // working fine
	public List<TaskInfo> retrieveAllTasksForUser(@RequestParam String status , @Valid @RequestParam Long userId) {
		return service.retrieveTasksByStatusforUser(status,userId);
	}

	@DeleteMapping("/users/tasks/{taskId}") // working fine
	public boolean deleteTaskById(@Valid @PathVariable Long taskId) {
		return service.deleteTask(taskId);
	}

	@PostMapping(value= "/users/{userId}/tasks") // working fine
	public ResponseEntity<TaskInfo> createTask(@Valid @RequestBody TaskInfo entity, @Valid @PathVariable Long userId) {
		Long createdTaskId = service.saveTask(entity, userId);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{taskId}").buildAndExpand(createdTaskId)
				.toUri();

		return ResponseEntity.created(location).build();
	}

	@PutMapping(value = "/users/{userId}/tasks/{taskId}") // working fine
	public TaskInfo updateTask(@Valid @RequestBody TaskInfo entity, @Valid @PathVariable Long taskId, @Valid @PathVariable Long userId) {

		return service.retrieveTaskById(service.updateTask(entity, taskId, userId));

	}
}
