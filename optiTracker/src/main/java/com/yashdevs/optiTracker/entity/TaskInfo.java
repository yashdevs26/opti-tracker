package com.yashdevs.optiTracker.entity;

import java.time.Duration;
import java.time.LocalDateTime;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PostLoad;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@NamedQueries(value = {
		@NamedQuery(name = TaskInfo.GET_TASK_INFO_FOR_DATE, query = " select task from TaskInfo task where task.taskTargetTime = :dtime and task.user.userId = :userId "),
		@NamedQuery(name = TaskInfo.GET_ALL_TASK_INFO_FOR_USER, query = " select task from TaskInfo task where task.user.userId = :userId "),
		@NamedQuery(name = TaskInfo.GET_TASK_INFO_FOR_USER_BY_STATUS, query = " select task from TaskInfo task where task.user.userId = :userId and task.taskStatus in "
				+ " (:status) ") })

@Entity
@Table(name = "TASK_INFO")
public class TaskInfo {

	public static final String GET_TASK_INFO_FOR_DATE = "TaskInfo.getTaskForDate";
	public static final String GET_ALL_TASK_INFO_FOR_USER = "TaskInfo.getAllTaskForUser";
	public static final String GET_TASK_INFO_FOR_USER_BY_STATUS = "TaskInfo.getTaskByStatus";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "TASK_ID")
	private Long taskId;

	@Column(name = "DESCRIPTION")
	private String taskDescription;

	@Column(name = "STATUS")
	private String taskStatus;

	@Column(name = "START_TIME")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime taskStartTime;

	@Column(name = "TARGET_TIME")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime taskTargetTime;

	@Column(name = "END_TIME")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime taskEndTime;

	@Column(name = "CATEGORY")
	private String taskCategory;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID")
	@JsonIgnore
	private UserInfo user;

	@Transient
	//@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
	private Duration timeTaken = Duration.ZERO;

	@Transient
	//@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
	private Duration timeDifference = Duration.ZERO;

	public Long getTaskId() {
		return taskId;
	}

	public String getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(String taskStatus) {
		this.taskStatus = taskStatus;
	}

	public String getTaskDescription() {
		return taskDescription;
	}

	public void setTaskDescription(String taskDescription) {
		this.taskDescription = taskDescription;
	}

	public LocalDateTime getTaskStartTime() {
		return taskStartTime;
	}

	public void setTaskStartTime(LocalDateTime taskStartTime) {
		this.taskStartTime = taskStartTime;
	}

	public LocalDateTime getTaskTargetTime() {
		return taskTargetTime;
	}

	public void setTaskTargetTime(LocalDateTime taskTargetTime) {
		this.taskTargetTime = taskTargetTime;
	}

	public LocalDateTime getTaskEndTime() {
		return taskEndTime;
	}

	public void setTaskEndTime(LocalDateTime taskEndTime) {
		this.taskEndTime = taskEndTime;
	}

	public String getTaskCategory() {
		return taskCategory;
	}

	public void setTaskCategory(String taskCategory) {
		this.taskCategory = taskCategory;
	}

	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}

	public UserInfo getUser() {
		return user;
	}

	public void setUser(UserInfo user) {
		this.user = user;
	}

	public Duration getTimeTaken() {
		return timeTaken;
	}

	public Duration getTimeDifference() {
		return timeDifference;
	}

	@PostLoad
	public void setTransients() {
		if (taskStartTime != null && taskEndTime != null) {
			this.timeTaken = Duration.between(taskStartTime, taskEndTime);
		}
		if (taskTargetTime != null && taskEndTime != null) {
			this.timeDifference = Duration.between(taskTargetTime, taskEndTime);
		}
	}

}
