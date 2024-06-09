package com.yashdevs.optiTracker.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.yashdevs.optiTracker.dto.LoginResponseDto;
import com.yashdevs.optiTracker.entity.UserInfo;
import com.yashdevs.optiTracker.exceptions.UserNotFoundException;
import com.yashdevs.optiTracker.repository.provider.UserRepositoryProvider;
import com.yashdevs.optiTracker.resource.OptiTrackerConstants;

import jakarta.persistence.NoResultException;
import jakarta.validation.Valid;

@Service
public class UserServiceImpl implements UserService {

	private UserRepositoryProvider repo;
	private EmailVerificationService emailService;
	private JwtTokenService tokenService;
	private AuthenticationManager authenticationManager;
	private PasswordEncoder encoder;

	public UserServiceImpl(UserRepositoryProvider repo, EmailVerificationService emailService,
			JwtTokenService tokenService, AuthenticationManager authenticationManager, PasswordEncoder encoder) {
		this.repo = repo;
		this.emailService = emailService;
		this.authenticationManager = authenticationManager;
		this.tokenService = tokenService;
		this.encoder = encoder;
	}

	@Override
	public UserInfo retrieveUserByUserId(Long id) {

		try {
			Optional<UserInfo> userInfo = repo.findById(id);
			if (userInfo.isPresent()) {
				return userInfo.get();
			} else {
				throw new UserNotFoundException("user Id : " + id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<UserInfo> retrieveAllUsers() {
		try {
			return repo.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}

	@Override
	public Long saveUser(UserInfo entity) {

		UserInfo user = null;
		try {
			if (ObjectUtils.isEmpty(entity.getUserId())) {
				entity.setStatus(OptiTrackerConstants.USER_PENDING);
				entity.setPassword(encoder.encode(entity.getPassword()));
				entity.setEmailToken(emailService.generateToken());
			}
			user = repo.save(entity);

			if (!ObjectUtils.isEmpty(user)) {
				if (user.getStatus().equals(OptiTrackerConstants.USER_PENDING)) {
					emailService.validateEmail(user.getEmail(), user.getEmailToken());
				}
				return user.getUserId();
			} else {
				throw new RuntimeException("Entity Insertion failed");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return 0L;
		}
	}

	@Override
	public boolean deleteUser(Long id) {

		try {
			repo.deleteById(id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Long updateUser(UserInfo entity, Long userId) {
		try {
			Optional<UserInfo> user = repo.findById(userId);

			if (user.isPresent()) {
				if (!StringUtils.isBlank(entity.getFullName())) {
					user.get().setFullName(entity.getFullName());
				}
				if (!StringUtils.isBlank(entity.getUserNameEntity())) {
					user.get().setUserNameEntity(entity.getUserNameEntity());
				}
				return saveUser(user.get());
			} else
				throw new UserNotFoundException("User not found for this user Id :" + userId);

		} catch (Exception e) {
			e.printStackTrace();
			return 0L;
		}
	}

	@Override
	public UserInfo retrieveUserByEmailPass(String emailId, String pass) {
		try {
			UserInfo user = repo.findUserByEmailPass(emailId, pass);
			if (user.getUserId() != null) {
				return user;
			} else {
				throw new UserNotFoundException("email Id : " + emailId + " and password: " + pass);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public LoginResponseDto loginUser(String email, String password) {

		UserInfo user = repo.findUserByEmail(email.trim());
		if (user == null || !encoder.matches(password.trim(), user.getPassword())) {
			throw new UserNotFoundException("Bad Credentials");
		}

		var token = tokenService.generateToken(
				authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password)));

		Long userId = 0L;

		if (!StringUtils.isBlank(token)) {

			userId = user.getUserId();
		}
		return new LoginResponseDto(token, "90", userId);
	}

	@Override
	public boolean verifyEmail(@Valid String emailToken) {
		try {
			UserInfo user = repo.findUserByEmailToken(emailToken);
			if (user == null) {
				return false;
			} else {
				user.setStatus(OptiTrackerConstants.USER_ACTIVE);
				this.saveUser(user);
				return true;
			}
		} catch (Exception e) {
			return false;
		}

	}

	@Override
	public boolean verfyNewUser(@Valid String email) {

		try {
			if (repo.findUserByEmail(email) == null) {
				return true;
			} else
				return false;
		} catch (NoResultException e) {
			return false;
		}
	}
}
