package com.yashdevs.optiTracker.repository.provider;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.yashdevs.optiTracker.entity.UserInfo;
import com.yashdevs.optiTracker.repository.UserRepository;

@Repository
@Transactional
public interface UserRepositoryProvider extends JpaRepository<UserInfo,  Long>, UserRepository{

}
