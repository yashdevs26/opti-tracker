package com.yashdevs.optiTracker.repository.provider;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.yashdevs.optiTracker.entity.TaskInfo;
import com.yashdevs.optiTracker.repository.TaskRepository;

@Repository
@Transactional
public interface TaskRepositoryProvider extends JpaRepository<TaskInfo, Long>, TaskRepository{

}
