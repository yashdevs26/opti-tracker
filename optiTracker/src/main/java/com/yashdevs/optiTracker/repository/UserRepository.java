package com.yashdevs.optiTracker.repository;

import com.yashdevs.optiTracker.entity.UserInfo;

public interface UserRepository {

	public UserInfo findUserByEmailPass(String email, String pass);
	public UserInfo findUserByEmail(String email);
	public UserInfo findUserByEmailToken(String token);

}
