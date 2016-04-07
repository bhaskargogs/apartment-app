package com.apartment.service;

import java.util.List;

import com.apartment.controller.model.User;

public interface IntAuthService {
	public User addProfile(User user);

	public String authenticateUser(String username, String password);

	public List<User> findUsers();

	public String deleteUserProfileByUsername(String username);

	public User findUserProfileByUsername(String username);

	public String updateProfile(User entity);
}
