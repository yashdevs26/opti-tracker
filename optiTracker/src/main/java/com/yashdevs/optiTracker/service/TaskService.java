package com.yashdevs.optiTracker.service;

import java.util.List;

import com.yashdevs.optiTracker.entity.TaskInfo;

public interface TaskService {
	
	public TaskInfo retrieveTaskById(Long id);
	
	public List<TaskInfo> retrieveAllTasks();
	
	public Long saveTask(TaskInfo entity, Long userId);
	
	public boolean deleteTask(Long id);
	
	public List<TaskInfo> retrieveTaskByDateForUser(String datetime, Long userId);
	
	public List<TaskInfo> retrieveAllTasksForUser(Long userId);
	
	public List<TaskInfo> retrieveTasksByStatusforUser(String status, Long userId);
	
	public Long updateTask(TaskInfo entity, Long taskId, Long userId);
}
