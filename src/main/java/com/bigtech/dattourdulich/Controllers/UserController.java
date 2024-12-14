package com.bigtech.dattourdulich.Controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bigtech.dattourdulich.Daoiplm.UserDao;
import com.bigtech.dattourdulich.models.UserEntity;

@Controller
public class UserController {
	
	UserDao userDao;
	
	@Autowired
	public UserController(UserDao userDao) {
		super();
		this.userDao = userDao;
	}

	public void checkUserAuthorities() {
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    if (authentication != null && authentication.isAuthenticated()) {
	        System.out.println("Current User: " + authentication.getName()); 
	        System.out.println(authentication.isAuthenticated());
	        System.out.println(authentication.getAuthorities());

	        // In danh sách quyền của user
	        for (GrantedAuthority authority : authentication.getAuthorities()) {
	            System.out.println("Authority: " + authority.getAuthority());
	        }
	    } else {
	        System.out.println("No authenticated user.");
	    }
	}
	
	@RequestMapping("/home")
	public String home(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		System.out.println(authentication.getName());
		UserEntity userinfo = userDao.getByUserName(authentication.getName());
		model.addAttribute("userinfo", userinfo);
		return "/auth/home";
	}
	@RequestMapping("/user/footer")
	public String getFooter() {
		return "auth/footer";
	}
	@RequestMapping("/user/nav")
	public String getNav() {
		return "auth/nav";
	}
	@RequestMapping("/user/info")
	public String getInfo() {
		return "auth/info";
	}
	
}
