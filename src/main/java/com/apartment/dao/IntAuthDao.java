package com.apartment.dao;

import java.util.List;

import com.apartment.dao.entity.UserEntity;

public interface IntAuthDao {
	public UserEntity addProfile(UserEntity entity);

	public String authenticateUser(String username, String password);

	public UserEntity findRoleByUsername(String username);

	public List<UserEntity> findUsers();

	public String deleteUserProfileByUsername(String username);

	public UserEntity findUserProfileByUsername(String username);

	public String updateProfile(UserEntity entity);
}
