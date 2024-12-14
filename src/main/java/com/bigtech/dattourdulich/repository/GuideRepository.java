package com.bigtech.dattourdulich.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bigtech.dattourdulich.models.tour_guides;
import java.util.List;


public interface GuideRepository extends JpaRepository<tour_guides, Integer> {
	List<tour_guides> findByGuideName(String guideName);
}
