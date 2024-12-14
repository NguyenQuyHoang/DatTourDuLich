package com.bigtech.dattourdulich.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bigtech.dattourdulich.models.Itinerary;
import com.bigtech.dattourdulich.models.itinerary_nodes;

@Repository
public interface itrNodeRepository extends JpaRepository<itinerary_nodes, Integer> {
	List<itinerary_nodes> findByItinerary(Itinerary itinerary);
}
