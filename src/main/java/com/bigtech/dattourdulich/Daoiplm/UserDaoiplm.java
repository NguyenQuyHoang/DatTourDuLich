package com.bigtech.dattourdulich.Daoiplm;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import com.bigtech.dattourdulich.models.UserEntity;
import com.bigtech.dattourdulich.repository.userRepository;

@Repository
public class UserDaoiplm implements UserDao {
	
	userRepository userrepository;
	
	
	@Autowired
	public UserDaoiplm(userRepository userrepository) {
		super();
		this.userrepository = userrepository;
	}
	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	@Override
	public UserEntity getByUserName(String name) {
		Optional<UserEntity> user = userrepository.findByUsername(name);
		UserEntity newUser = user.get();
		return newUser;
	}

}
