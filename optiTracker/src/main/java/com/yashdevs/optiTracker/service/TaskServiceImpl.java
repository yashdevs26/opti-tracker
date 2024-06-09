package com.yashdevs.optiTracker.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.yashdevs.optiTracker.entity.TaskInfo;
import com.yashdevs.optiTracker.exceptions.TaskNotFoundException;
import com.yashdevs.optiTracker.repository.provider.TaskRepositoryProvider;
import com.yashdevs.optiTracker.resource.OptiTrackerConstants;

@Service
public class TaskServiceImpl implements TaskService {

	private TaskRepositoryProvider repo;
	private UserService userService;

	public TaskServiceImpl(TaskRepositoryProvider repo, UserService userService) {
		this.repo = repo;
		this.userService = userService;
	}

	@Override
	public TaskInfo retrieveTaskById(Long id) {
		try {
			Optional<TaskInfo> taskInfo = repo.findById(id);
			if (taskInfo.isPresent()) {
				return taskInfo.get();
			} else {
				throw new TaskNotFoundException("No Task Found for this id: " + id);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<TaskInfo> retrieveAllTasks() {
		try {
			return repo.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Collections.emptyList();
	}

	@Override
	public Long saveTask(TaskInfo entity, Long userId) {

		TaskInfo task = null;
		try {
			entity.setUser(userService.retrieveUserByUserId(userId));
			entity.setTaskStatus(OptiTrackerConstants.USER_PENDING);
			task = repo.save(entity);

			if (!ObjectUtils.isEmpty(task)) {
				return task.getTaskId();
			} else {
				return 0L;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return 0L;
		}
	}

	@Override
	public boolean deleteTask(Long id) {

		try {
			repo.deleteById(id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Long updateTask(TaskInfo entity, Long taskId, Long userId) {

		try {
			entity.setTaskId(taskId);
			return saveTask(entity, userId);

		} catch (Exception e) {
			e.printStackTrace();
			return 0L;
		}
	}

	@Override
	public List<TaskInfo> retrieveTaskByDateForUser(String datetime, Long userId) {

		try {
			return repo.findTasksByDateForUser(LocalDateTime.parse(datetime, DateTimeFormatter.ISO_LOCAL_DATE_TIME),
					userId);

		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}

	@Override
	public List<TaskInfo> retrieveAllTasksForUser(Long userId) {
		try {
			return repo.findAllTasksForUser(userId);

		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}

	@Override
	public List<TaskInfo> retrieveTasksByStatusforUser(String status, Long userId) {
		try {
			return repo.findTasksByStatusforUser(status, userId);

		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}
}
