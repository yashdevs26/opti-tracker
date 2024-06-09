package com.yashdevs.optiTracker.repository;

import java.time.LocalDateTime;
import java.util.List;

import com.yashdevs.optiTracker.entity.TaskInfo;

public interface TaskRepository {
	
	public List<TaskInfo> findTasksByDateForUser(LocalDateTime date, Long userId);
	
	public List<TaskInfo> findAllTasksForUser(Long userId);
	
	public boolean markPendingTasksAsFreezed(List<TaskInfo> tasks);
	
	public List<TaskInfo> findTasksByStatusforUser(String status, Long userId);
}
