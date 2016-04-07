package com.apartment.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.apartment.controller.model.User;
import com.apartment.dao.entity.UserEntity;
import com.apartment.service.IntAuthService;

@Controller
public class ProfileController {
	@Autowired
	@Qualifier("service")
	private IntAuthService authService;
	
	@RequestMapping(value="kill",method=RequestMethod.GET)
	public String killPage() {
		return "kill";
	}
	
	@RequestMapping(value="sortByEmailAsc",method=RequestMethod.GET)
	public String sortUserProfileEmail(Model model) {
		List<User> userProfiles=authService.findUsers();
		Collections.sort(userProfiles);
		model.addAttribute("userProfiles",userProfiles);
		return "users";
	}
	
	@RequestMapping(value="editProfile",method=RequestMethod.GET)
	public String editUserProfile(@RequestParam("username") String username,Model model) {
		//creating blank object of user profile
		User userProfile=authService.findUserProfileByUsername(username);
		model.addAttribute("profile",userProfile);
		return "editUserProfile";
	}
	
	@RequestMapping(value="editProfile",method=RequestMethod.POST)
	public String editUserProfileSubmit(@ModelAttribute("profile") User profile,Model model) {
		//creating blank object of user profile
		String userProfile=authService.updateProfile(profile);
		 List<User> userProfiles=authService.findUsers();
		model.addAttribute("userProfiles",userProfiles);
		return "users";
	}
	
	@RequestMapping(value="deleteUserProfile",method=RequestMethod.GET)
	public String profiles(@RequestParam("username") String username,Model model) {
		//creating blank object of user profile
		String result=authService.deleteUserProfileByUsername(username);
	     List<User> userProfiles=authService.findUsers();
		model.addAttribute("userProfiles",userProfiles);
		return "users";
	}
	
	@RequestMapping(value="profiles",method=RequestMethod.GET)
	public String profiles(Model model) {
		//creating blank object of user profile
		 List<User> userProfiles=authService.findUsers();
		model.addAttribute("userProfiles",userProfiles);
		return "users";
	}
	
	@RequestMapping(value="userProfile",method=RequestMethod.GET)
	public String userProfilePage(Model model) {
		//creating blank object of user profile
		UserEntity profile=new UserEntity();
		model.addAttribute("profile",profile);
		return "register";
	}
	
	@RequestMapping(value="userProfile",method=RequestMethod.POST)
	public String userProfileSubmit(@ModelAttribute("profile") User profile,Model model) {
		//creating blank object of user profile
		System.out.println(profile);
		authService.addProfile(profile);
		model.addAttribute("ApplicationMessage", "User profile is created successfully for username "+profile.getUsername());
		return "login";
	}
}
