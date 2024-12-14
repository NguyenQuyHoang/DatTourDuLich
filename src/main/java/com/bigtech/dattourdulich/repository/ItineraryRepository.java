package com.bigtech.dattourdulich.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bigtech.dattourdulich.models.Itinerary;
import com.bigtech.dattourdulich.models.tour;

@Repository
public interface ItineraryRepository extends JpaRepository<Itinerary, Integer> {
    List<Itinerary> findByTour(tour tour);; // Tìm tất cả lịch trình theo ID tour
}

