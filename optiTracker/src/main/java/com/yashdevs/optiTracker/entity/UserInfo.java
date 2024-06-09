package com.yashdevs.optiTracker.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yashdevs.optiTracker.resource.OptiTrackerConstants;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@NamedQueries(value = { 
	@NamedQuery(name = UserInfo.GET_USER_INFO_FOR_LOGIN, query = " select user from UserInfo user where user.email = :email and user.password = :password and user.status "
			+ " in ( '"+ OptiTrackerConstants.USER_ACTIVE + "' , '" + OptiTrackerConstants.USER_PENDING +"' )" ),
	@NamedQuery(name = UserInfo.GET_USER_INFO_BY_EMAIL, query = " select user from UserInfo user where user.email = :email and user.status "
			+ " in ( '"+ OptiTrackerConstants.USER_ACTIVE + "' , '" + OptiTrackerConstants.USER_PENDING +"' )" ),
	@NamedQuery(name = UserInfo.GET_USER_INFO_BY_EMAIL_TOKEN, query = " select user from UserInfo user where user.emailToken = :emailToken and user.status "
			+ " in ( '"+ OptiTrackerConstants.USER_ACTIVE + "' , '" + OptiTrackerConstants.USER_PENDING +"' )" )
	
	})

@Entity
@Table(name= "USER_INFO")
public class UserInfo implements UserDetails {

	private static final long serialVersionUID = 1L;

	public static final String GET_USER_INFO_FOR_LOGIN = "UserInfo.getUserForLogin";
	public static final String GET_USER_INFO_BY_EMAIL = "UserInfo.getUserByEmail";
	public static final String  GET_USER_INFO_BY_EMAIL_TOKEN = "UserInfo.getUserByEmailToken";
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="USER_ID")
	private Long userId;
	
	@Column(name = "USER_NAME")
	@NotBlank
	private String userNameEntity;
	
	@Column(name = "FULL_NAME")
	@Size(min = 1, max = 200)
	private String fullName;
	
	@Column(name = "EMAIL")
	@Email
	@Size(min = 1, max = 150)
	@NotBlank
	private String email;
	
	@Column(name = "USER_KEY")
	@NotBlank
	@JsonIgnore
	private String password;
	
	@Column(name = "STATUS")
	private String status;
	
	@Column(name = "EMAIL_TOKEN")
	@JsonIgnore
	private String emailToken;
	
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	private List<TaskInfo> tasks = new ArrayList<>();

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<TaskInfo> getTasks() {
		return tasks;
	}

	public String getEmailToken() {
		return emailToken;
	}

	public void setEmailToken(String emailToken) {
		this.emailToken = emailToken;
	}

	public String getUserNameEntity() {
		return userNameEntity;
	}

	public void setUserNameEntity(String userName) {
		this.userNameEntity = userName;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of();
	}

	@Override
	public String getUsername() {
		return getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
	public UserInfo() {
		// TODO Auto-generated constructor stub
	}
	
	
}
