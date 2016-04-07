package com.apartment.controller;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.apartment.service.IntAuthService;

@Controller
public class LoginController {
	
	@Autowired
	@Qualifier("service")
	private IntAuthService authService;
	
	@RequestMapping(value="homePage",method = RequestMethod.GET)
    public String handleRequestInternal(Model model) throws Exception {
       Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
       //retrieving the role of the logged in user.
       Collection<? extends GrantedAuthority> grantedList=authentication.getAuthorities();
       //Here we are assuming last role present inside the list will be actual role of
       //logged in user.
       String nextPage="";
       if(grantedList!=null && grantedList.size()>0){
         Iterator<? extends GrantedAuthority> iterator=grantedList.iterator();
         if(iterator.hasNext()){
             GrantedAuthority ga=iterator.next();
             nextPage=ga.getAuthority(); 
         }
       }
       return "redirect:/apartment/profiles";
    }
	
	@RequestMapping(value="denied",method=RequestMethod.GET)
	public String denied(HttpServletRequest request) {
		return "accessDenied";
	}
	
	
	@RequestMapping(value="invalidLogin",method=RequestMethod.GET)
	public String invalidLogin() {
		return "invalidLogin";
	}
	
	@RequestMapping(value="auth",method=RequestMethod.GET)
	public String authLogin(HttpServletRequest request) {
		return "login";
	}
	
	@RequestMapping(value="auth",method=RequestMethod.POST)
	public String auth(HttpServletRequest request) {
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		//here we are calling dao layer which is authentication with db
		String result=authService.authenticateUser(username, password);
		request.setAttribute("email",username);
		if("success".equalsIgnoreCase(result)){
			 return "redirect:/apartment/profiles";
		}	
		else{
			request.setAttribute("ApplicationMessage","Sorry you cannot access the app");
		}
		return "login";
	}
	
}
