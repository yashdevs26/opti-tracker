package com.yashdevs.optiTracker.controller;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.yashdevs.optiTracker.dto.JwtTokenRequestDto;
import com.yashdevs.optiTracker.dto.LoginResponseDto;
import com.yashdevs.optiTracker.dto.RegisterUserDto;
import com.yashdevs.optiTracker.entity.UserInfo;
import com.yashdevs.optiTracker.exceptions.UserAlreadyExistsException;
import com.yashdevs.optiTracker.service.UserService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/auth")
public class UserAuthController {

	private UserService service;

	public UserAuthController(UserService service) {
		this.service = service;
	}

	@PostMapping("/signup")
    public ResponseEntity<UserInfo> registerUser(@RequestBody @Valid RegisterUserDto register) {
		
		if(!service.verfyNewUser(register.getEmail().trim())) {
			throw new UserAlreadyExistsException("user with same email id already exists");
		}
		
		service.saveUser(register.toUserInfo());
		
		String currentPath = ServletUriComponentsBuilder.fromCurrentRequest().toString().replaceFirst("signup", "login");
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().replacePath(currentPath).build().toUri();

        return ResponseEntity.created(location).build();
    }

	@PostMapping("/login")
	public ResponseEntity<LoginResponseDto> loginUser(@Valid @RequestBody JwtTokenRequestDto loginCreds) {
		LoginResponseDto loginUser = service.loginUser(loginCreds.email(), loginCreds.password());
		
		return ResponseEntity.ok(loginUser);
	}
	
	@PostMapping("/verify/{emailToken}")
	public String verifyEmail(@Valid @PathVariable String emailToken) {
		boolean isVerified = service.verifyEmail(emailToken);
		
		if(isVerified) {
			return "successful";
		}
		else {
			return "unsuccessful";
		}
	}

}
