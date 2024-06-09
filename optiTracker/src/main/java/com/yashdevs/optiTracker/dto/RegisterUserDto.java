package com.yashdevs.optiTracker.dto;

import com.yashdevs.optiTracker.entity.UserInfo;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

//@Data
public class RegisterUserDto {

	public RegisterUserDto() {
   // TODO document why this constructor is empty
 }
	@NotNull(message = "User Name is required.")
	@Size(min = 5, message = "User Name should have at least 5 characters")
	@Size(max = 15, message = "User Name should have at most 15 characters")
	private String userName;

	@NotNull(message = "Name is required")
	@Size(min = 1, message = "Name should have at least 1 character")
	@Size(max = 200, message = "Name should have at most 200 characters")
	private String fullName;

	@NotNull(message = "Email address is required")
	@Email
	private String email;

	@NotNull(message = "Password is required")
	@Size(min = 8, message = "Name should have at least 8 characters")
	@Size(max = 20, message = "Name should have at most 20 characters")
	@Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,20}$",
			/*
			 * 
			 * ^: indicates the string’s beginning (?=.*[a-z]): makes sure that there is at
			 * least one small letter (?=.*[A-Z]): needs at least one capital letter
			 * (?=.*\\d): requires at least one digit (?=.*[@#$%^&+=]): provides a guarantee
			 * of at least one special symbol .{8,20}: imposes the minimum length of 8
			 * characters and the maximum length of 20 characters $: terminates the string
			 * 
			 */
			// flags = { Flag.CASE_INSENSITIVE },
			message = "The password is invalid.")
	private String password;

	/*
	 * @NotNull(message = "The departure date is required.")
	 * 
	 * @FutureOrPresent(message =
	 * "The departure date must be today or in the future.") private Date
	 * departureDate;
	 * 
	 * @NotNull(message = "The arrival date is required.")
	 * 
	 * @FutureOrPresent(message =
	 * "The arrival date must be today or in the future.") private Date arrivalDate;
	 */

	public UserInfo toUserInfo() {
		UserInfo user = new UserInfo();
		user.setEmail(email.toLowerCase());
		user.setUserNameEntity(userName);
		user.setPassword(password);
		user.setFullName(fullName);
		
		return user;

	}

	public String getEmail() {
		return email;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
