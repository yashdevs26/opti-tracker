/**
 * 
 */
package com.yashdevs.optiTracker.repository;

import java.time.LocalDateTime;
import java.util.List;

import com.yashdevs.optiTracker.entity.TaskInfo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

/**
 * 
 */
public class TaskRepositoryImpl implements TaskRepository {

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<TaskInfo> findTasksByDateForUser(LocalDateTime date, Long userId) {
		TypedQuery<TaskInfo> query = em.createNamedQuery(TaskInfo.GET_TASK_INFO_FOR_DATE, TaskInfo.class);
		query.setParameter("dtime", date);
		query.setParameter("userId", userId);

		return query.getResultList();
	}

	@Override
	public List<TaskInfo> findAllTasksForUser(Long userId) {
		TypedQuery<TaskInfo> query = em.createNamedQuery(TaskInfo.GET_ALL_TASK_INFO_FOR_USER, TaskInfo.class);
		query.setParameter("userId", userId);

		return query.getResultList();
	}

	@Override
	public boolean markPendingTasksAsFreezed(List<TaskInfo> tasks) {
		return false;
	}

	@Override
	public List<TaskInfo> findTasksByStatusforUser(String status, Long userId) {
		TypedQuery<TaskInfo> query = em.createNamedQuery(TaskInfo.GET_TASK_INFO_FOR_USER_BY_STATUS, TaskInfo.class);
		query.setParameter("userId", userId);
		query.setParameter("status", status);

		return query.getResultList();
	}

}
