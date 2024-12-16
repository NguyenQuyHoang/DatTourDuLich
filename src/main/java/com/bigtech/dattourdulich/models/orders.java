package com.bigtech.dattourdulich.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @OneToOne
    @JoinColumn(name = "tour_id", nullable = false)
    private tour tour;
    
    @OneToOne
    @JoinColumn(name = "itineraries_id", nullable = false)
    private Itinerary itinerary;
    
    @OneToOne
    @JoinColumn(name = "guide_id", nullable = false)
    private tour_guides tourguides;
    
    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity userEntity;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public tour getTour() {
		return tour;
	}

	public void setTour(tour tour) {
		this.tour = tour;
	}

	public Itinerary getItinerary() {
		return itinerary;
	}

	public void setItinerary(Itinerary itinerary) {
		this.itinerary = itinerary;
	}

	public tour_guides getTourguides() {
		return tourguides;
	}

	public void setTourguides(tour_guides tourguides) {
		this.tourguides = tourguides;
	}

	public UserEntity getUserEntity() {
		return userEntity;
	}

	public void setUserEntity(UserEntity userEntity) {
		this.userEntity = userEntity;
	}

	public orders(com.bigtech.dattourdulich.models.tour tour, Itinerary itinerary, tour_guides tourguides,
			UserEntity userEntity) {
		super();
		this.tour = tour;
		this.itinerary = itinerary;
		this.tourguides = tourguides;
		this.userEntity = userEntity;
	}

	public orders() {
		super();
	}
    
    
    
}
