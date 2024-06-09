package com.yashdevs.optiTracker.repository;

import com.yashdevs.optiTracker.entity.UserInfo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

public class UserRepositoryImpl implements UserRepository {

	@PersistenceContext
	private EntityManager em;

	@Override
	public UserInfo findUserByEmailPass(String email, String pass) {

		UserInfo user = null;
		TypedQuery<UserInfo> query = em.createNamedQuery(UserInfo.GET_USER_INFO_FOR_LOGIN, UserInfo.class);
		query.setParameter("email", email);
		query.setParameter("password", pass);

		user = query.getSingleResult();
		return user;
	}

	@Override
	public UserInfo findUserByEmail(String email) {

		try {
			UserInfo user = null;
			TypedQuery<UserInfo> query = em.createNamedQuery(UserInfo.GET_USER_INFO_BY_EMAIL, UserInfo.class);
			query.setParameter("email", email.trim());
			user = query.getSingleResult();

			return user;

		} catch (Exception e) {
			//e.printStackTrace();
			return null;
		}
	}

	@Override
	public UserInfo findUserByEmailToken(String token) {
		UserInfo user = null;
		try {

			TypedQuery<UserInfo> query = em.createNamedQuery(UserInfo.GET_USER_INFO_BY_EMAIL_TOKEN, UserInfo.class);
			query.setParameter("emailToken", token.trim());
			user = query.getSingleResult();
			return user;

		} catch (Exception e) {
			e.printStackTrace();
			return user;
		}
	}

}
