package com.apartment.dao.impl;

import java.sql.Types;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.apartment.dao.IntAuthDao;
import com.apartment.dao.entity.UserEntity;
//import com.mobile.app.dao.entity.UserProfileEntity;

@Repository("dao")
@Scope("singleton")
public class AuthDao implements IntAuthDao {

	@Autowired
	@Qualifier("aptJdbc")
	private JdbcTemplate jdbc;

	@Override
	@CachePut(value = "spring-cache", key = "#entity.username")
	public String updateProfile(UserEntity entity) {
		String sql = "update user set fname=?,lname=?,password=?,email=?,birthday=?,role=? where username=?";
		Object data[] = new Object[] { entity.getFirstName(), entity.getLastName(), entity.getPassword(),entity.getEmail(),
				entity.getBirthday(), entity.getRole(), entity.getUsername() };
		jdbc.update(sql, data);
		return "update";
	}

	@Override
	@CacheEvict(value = "spring-cache", allEntries = true)
	public UserEntity addProfile(UserEntity entity) {
		int[] dataType = new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,Types.VARCHAR, Types.VARCHAR, Types.DATE,
				Types.VARCHAR };
		String query = "insert into user(fname,lname,email,username,password,birthday,role) values(?,?,?,?,?,?,?)";
		Object data[] = new Object[] { entity.getFirstName(), entity.getLastName(), entity.getEmail(),entity.getUsername(),
				entity.getPassword(), entity.getBirthday(), entity.getRole() };
		jdbc.update(query, data, dataType);
		return entity;
	}

	@Override
	public String authenticateUser(String username, String password) {
		String message = "";
		String sql = "select role from user where username=? and password=?";
		Object data[] = new Object[] { username, password };
		try {
			String role = (String) jdbc.queryForObject(sql, data, String.class);
			message = "success";
		} catch (Exception e) {
			message = "fail";
		}
		return message;
	}

	@Override
	@Cacheable(value = "spring-cache", key = "#userProfile.username")
	public UserEntity findUserProfileByUsername(String username) {
		UserEntity userProfile = null;
		String sql = "select * from user where username=?";
		Object data[] = new Object[] { username };
		try {
			userProfile = (UserEntity) jdbc.queryForObject(sql, data, new BeanPropertyRowMapper(UserEntity.class));
		} catch (Exception e) {
		}
		return userProfile;
	}

	@CacheEvict(value = "spring-cache", allEntries = true)
	@Override
	public String deleteUserProfileByUsername(String username) {
		String sql = "delete  from user where username=?";
		int p = 0;
		try {
			p = jdbc.update(sql, new Object[] {username });
		} catch (Exception e) {
		}
		return p > 0 ? "success" : "failed";
	}

	@Override
	@Cacheable(value = "spring-cache")
	public List<UserEntity> findUsers() {
		System.out.println("_______________________________________________");
		System.out.println("_______________________________________________");
		System.out.println("_@)@)@)@(@((@findUserProfiles is called___________ ");
		System.out.println("_______________________________________________");
		System.out.println("_______________________________________________");
		List<UserEntity> userProfiles = null;
		String sql = "select * from user";
		try {
			userProfiles = (List<UserEntity>) jdbc.query(sql, new BeanPropertyRowMapper(UserEntity.class));
		} catch (Exception e) {
		}
		return userProfiles;
	}

	@Override
	public UserEntity findRoleByUsername(String username) {
		UserEntity user = null;
		String sql = "select * from user where username=?";
		Object data[] = new Object[] {username};
		try {
			user = (UserEntity) jdbc.queryForObject(sql, data, new BeanPropertyRowMapper(UserEntity.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

}
