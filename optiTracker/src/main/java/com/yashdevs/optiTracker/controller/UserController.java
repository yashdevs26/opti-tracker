package com.yashdevs.optiTracker.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yashdevs.optiTracker.entity.UserInfo;
import com.yashdevs.optiTracker.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

	private UserService service;

	public UserController(UserService service) {
		this.service = service;
	}

	@GetMapping("/") // working
	public ResponseEntity<List<UserInfo>> retrieveAllUsers() {
		return new ResponseEntity<>(service.retrieveAllUsers(), HttpStatus.OK);
	}

	@GetMapping("/{userId}") // working
	public ResponseEntity<UserInfo> retrieveUserById(@Valid @PathVariable Long userId) {
		return new ResponseEntity<>(service.retrieveUserByUserId(userId), HttpStatus.OK);
	}

	@DeleteMapping("/{userId}") // working
	public ResponseEntity<Boolean> deleteUserById(@Valid @PathVariable Long userId) {
		return new ResponseEntity<>(service.deleteUser(userId), HttpStatus.OK);
	}

	@PutMapping(value = "/{userId}") // working
	public ResponseEntity<UserInfo> updateUser(@PathVariable Long userId, @Valid @RequestBody UserInfo entity) {
		return new ResponseEntity<>(service.retrieveUserByUserId(service.updateUser(entity, userId)), HttpStatus.OK);
	}

	@GetMapping(value = "", params = { "emailId", "password" }) // working
	public ResponseEntity<UserInfo> retrieveUserLogin(@Valid @RequestParam String emailId, @RequestParam String password) {
		return new ResponseEntity<>(service.retrieveUserByEmailPass(emailId, password), HttpStatus.OK);
	}

}
