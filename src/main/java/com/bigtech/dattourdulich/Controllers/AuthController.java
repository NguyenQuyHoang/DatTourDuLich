package com.bigtech.dattourdulich.Controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bigtech.dattourdulich.models.Role;
import com.bigtech.dattourdulich.models.UserEntity;
import com.bigtech.dattourdulich.repository.roleRepository;
import com.bigtech.dattourdulich.repository.userRepository;

import jakarta.validation.Valid;

@Controller
public class AuthController {
	
	private roleRepository rolerepository;
	private userRepository userrepository;
	private PasswordEncoder passwordEncoder;
	
	@Autowired
    public AuthController(roleRepository rolerepository, PasswordEncoder passwordEncoder
    		, userRepository userrepository) {
		super();
		this.rolerepository = rolerepository;
		this.passwordEncoder = passwordEncoder;
		this.userrepository = userrepository;
	}

	@GetMapping("/register/user")
    public String showRegisterForm(Model model) {
        // Tạo đối tượng User trống để liên kết với form
        model.addAttribute("user", new UserEntity());
        return "auth/register"; // Tên file HTML
    }

    @PostMapping("/register/user")
    public String processRegister(@Valid @ModelAttribute("user") UserEntity user, BindingResult bindingResult, Model model) {
    	if (bindingResult.hasErrors()) {
    		return "auth/register"; 
    	}
    	
    	if (userrepository.existsByUsername(user.getUsername())) {
    		model.addAttribute("error", "username exists");
    		return "auth/register";
    	}
        Role user_role = rolerepository.findByName("USER").get();
        System.out.println("role name: " + user_role.getName());
        UserEntity newUser = new UserEntity();
        newUser.setUsername(user.getUsername());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        newUser.setEmail(user.getEmail());
        newUser.getRoles().add(user_role);
        
        userrepository.save(newUser);
        return "auth/login"; // Chuyển hướng đến trang thành công
    }
    
	@GetMapping("/register/admin")
    public String showRegisterAdForm(Model model) {
        // Tạo đối tượng User trống để liên kết với form
        model.addAttribute("user", new UserEntity());
        return "auth/registerAdmin"; // Tên file HTML
    }
	
	 @PostMapping("/register/admin")
	    public String processRegisterAd(@Valid @ModelAttribute("user") UserEntity user, BindingResult bindingResult, Model model) {
	    	if (bindingResult.hasErrors()) {
	    		return "auth/registerAdmin"; 
	    	}
	    	
	    	if (userrepository.existsByUsername(user.getUsername())) {
	    		model.addAttribute("error", "username exists");
	    		return "auth/registerAmin";
	    	}
	        Role admin_role = rolerepository.findByName("ADMIN").get();
	        Role user_role = rolerepository.findByName("USER").get();
	        System.out.println("role name: " + user_role.getName());
	        UserEntity newUser = new UserEntity();
	        newUser.setUsername(user.getUsername());
	        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
	        newUser.setEmail(user.getEmail());
	        newUser.getRoles().add(admin_role);
	        newUser.getRoles().add(user_role);
	        
	        
	        userrepository.save(newUser);
	        return "auth/login"; // Chuyển hướng đến trang thành công
	    }
    
    @GetMapping("/login")
    public String showLoginForm() {
        return "auth/login";
    }
}
