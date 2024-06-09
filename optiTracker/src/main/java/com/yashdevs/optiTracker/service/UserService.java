/**
 * 
 */
package com.yashdevs.optiTracker.service;

import java.util.List;

import com.yashdevs.optiTracker.dto.LoginResponseDto;
import com.yashdevs.optiTracker.entity.UserInfo;

import jakarta.validation.Valid;

/**
 * 
 */
public interface UserService {
	
	public List<UserInfo> retrieveAllUsers();
	
	public Long saveUser(UserInfo entity);
	
	public boolean deleteUser(Long id);
	
	public Long updateUser(UserInfo entity, Long userId);

	public UserInfo retrieveUserByEmailPass(String emailId, String pass);

	UserInfo retrieveUserByUserId(Long id);
	
	LoginResponseDto loginUser(String email, String password);

	public boolean verifyEmail(@Valid String emailToken);
	
	public boolean verfyNewUser(@Valid String email);
}
