package com.apartment.service.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.apartment.controller.model.User;
import com.apartment.dao.IntAuthDao;
import com.apartment.dao.entity.UserEntity;
import com.apartment.service.IntAuthService;

@Service("service")
@Scope("singleton")
public class AuthService implements IntAuthService {

	@Autowired
	@Qualifier("dao")
	private IntAuthDao auth;

	@Override
	public String updateProfile(User entity) {
		UserEntity user = new UserEntity();
		BeanUtils.copyProperties(entity, user);
		return auth.updateProfile(user);
	}

	@Override
	public User addProfile(User user) {
		UserEntity entity = new UserEntity();
		BeanUtils.copyProperties(user, entity);
		UserEntity userEntity = auth.addProfile(entity);
		BeanUtils.copyProperties(userEntity, user);
		return user;
	}

	@Override
	public String authenticateUser(String username, String password) {
		return auth.authenticateUser(username, password);
	}

	@Override
	public List<User> findUsers() {
		List<User> users = new ArrayList<>();
		List<UserEntity> entities = auth.findUsers();
		for (UserEntity userEntity : entities) {
			User user = new User();
			BeanUtils.copyProperties(userEntity, user);
			users.add(user);
		}
		return users;
	}

	@Override
	public String deleteUserProfileByUsername(String username) {
		return auth.deleteUserProfileByUsername(username);
	}

	@Override
	public User findUserProfileByUsername(String username) {
		User newUser = new User();
		UserEntity copyUser = auth.findUserProfileByUsername(username);
		BeanUtils.copyProperties(copyUser, newUser);
		return newUser;
	}

}
