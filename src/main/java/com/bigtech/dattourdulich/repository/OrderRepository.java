package com.bigtech.dattourdulich.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bigtech.dattourdulich.models.orders;

@Repository
public interface OrderRepository extends JpaRepository<orders, Integer> {

}
