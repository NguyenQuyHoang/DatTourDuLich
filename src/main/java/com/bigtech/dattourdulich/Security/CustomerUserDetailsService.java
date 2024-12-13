package com.bigtech.dattourdulich.Security;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bigtech.dattourdulich.models.Role;
import com.bigtech.dattourdulich.models.UserEntity;
import com.bigtech.dattourdulich.repository.userRepository;

@Service
public class CustomerUserDetailsService implements UserDetailsService {
	
	private userRepository userrepository;

	@Autowired
	public CustomerUserDetailsService(userRepository userrepository) {
		super();
		this.userrepository = userrepository;
	}



	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity user = userrepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("username not found"));
		System.out.println("Roles for user: " + user.getRoles());
		return new User(user.getUsername(), user.getPassword(), mapToAuthorities(user.getRoles()));
	}
	
	private Collection<GrantedAuthority> mapToAuthorities(List<Role> roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName())).collect(Collectors.toList());
	}

}
