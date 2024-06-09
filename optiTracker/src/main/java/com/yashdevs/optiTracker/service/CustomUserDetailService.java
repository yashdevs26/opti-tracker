package com.yashdevs.optiTracker.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.yashdevs.optiTracker.repository.provider.UserRepositoryProvider;

@Service
public class CustomUserDetailService implements UserDetailsService {

	private UserRepositoryProvider repo;

	public CustomUserDetailService(UserRepositoryProvider repo) {
		this.repo = repo;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return repo.findUserByEmail(username.trim());
	}

}
