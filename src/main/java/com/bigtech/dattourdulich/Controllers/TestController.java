package com.bigtech.dattourdulich.Controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class TestController {
	
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
	String home() {
		checkUserAuthorities();
		return "/auth/home";
	}
	
	@RequestMapping("/admin/info")
	String showInfo() {
		return "/auth/info";
	}
	
	@RequestMapping("/user/info")
	String showInfoUser() {
		return "/auth/info";
	}
}
