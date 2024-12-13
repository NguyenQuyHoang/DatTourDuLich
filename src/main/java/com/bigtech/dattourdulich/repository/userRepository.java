package com.bigtech.dattourdulich.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bigtech.dattourdulich.models.UserEntity;

public interface userRepository extends JpaRepository<UserEntity, Integer>{
	Optional<UserEntity> findByUsername(String username);
	boolean existsByUsername(String username);
}
