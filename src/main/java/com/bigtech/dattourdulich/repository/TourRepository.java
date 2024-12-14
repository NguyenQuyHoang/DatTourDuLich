package com.bigtech.dattourdulich.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bigtech.dattourdulich.models.tour;
import java.util.List;


public interface TourRepository extends JpaRepository<tour, Integer> {
	List<tour> findByTourName(String tourName);
}
