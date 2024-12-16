package com.bigtech.dattourdulich.Controllers;

import java.util.List;
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
import com.bigtech.dattourdulich.models.orders;
import com.bigtech.dattourdulich.models.tour;
import com.bigtech.dattourdulich.repository.OrderRepository;
import com.bigtech.dattourdulich.repository.TourRepository;

@Controller
public class UserController {
	
	UserDao userDao;
	OrderRepository orderRepository;
	TourRepository trp;
	
	@Autowired
	public UserController(UserDao userDao, OrderRepository orderRepository, TourRepository trp) {
		super();
		this.userDao = userDao;
		this.orderRepository = orderRepository;
		this.trp = trp;
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
	
	@GetMapping("/user/order")
    public String order(Model model) {
        List<tour> t = trp.findAll();
        model.addAttribute("tour", t);
        return "auth/order"; // Trả về trang order
	}
	


	
	@GetMapping("/mytour")
	public String myTour(Model model) {
	    model.addAttribute("id", "12345");
	    model.addAttribute("tour_id", "67890");
	    model.addAttribute("itineraries_id", "11122");
	    model.addAttribute("guide_id", "54321");
	    model.addAttribute("user_id", "98765");
	    return "/auth/mytour";
	}
	
	@Controller
	public class AboutController {
	    
	    @RequestMapping("/about")
	    public String about() {
	        return "/auth/about"; // Tên file HTML không có đuôi .html
	    }
	}
	
	@Controller
	public class ContactsController {
	    
	    @RequestMapping("/contacts")
	    public String about() {
	        return "/auth/contacts"; // Tên file HTML không có đuôi .html
	    }
	}
}
