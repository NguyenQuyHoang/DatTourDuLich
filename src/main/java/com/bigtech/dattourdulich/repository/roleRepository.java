package com.bigtech.dattourdulich.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bigtech.dattourdulich.models.Role;

public interface roleRepository extends JpaRepository<Role, Integer>{
	Optional<Role> findByName(String name);
}
