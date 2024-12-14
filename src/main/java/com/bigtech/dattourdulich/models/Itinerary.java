package com.bigtech.dattourdulich.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
public class Itinerary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // Các thuộc tính khác
    private String itineraries_name;
    private int day_num;
    private String descriptions;
    
    // Liên kết đến tour
    @ManyToOne
    @JoinColumn(name = "tour_id", nullable = false)
    private tour tour;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getItineraries_name() {
		return itineraries_name;
	}

	public void setItineraries_name(String itineraries_name) {
		this.itineraries_name = itineraries_name;
	}

	public int getDay_num() {
		return day_num;
	}

	public void setDay_num(int day_num) {
		this.day_num = day_num;
	}

	public String getDescriptions() {
		return descriptions;
	}

	public void setDescriptions(String descriptions) {
		this.descriptions = descriptions;
	}

	public tour getTour() {
		return tour;
	}

	public void setTour(tour tour) {
		this.tour = tour;
	}

	public Itinerary(String itineraries_name, int day_num, String descriptions,
			com.bigtech.dattourdulich.models.tour tour) {
		super();
		this.itineraries_name = itineraries_name;
		this.day_num = day_num;
		this.descriptions = descriptions;
		this.tour = tour;
	}
	
	

	public Itinerary(com.bigtech.dattourdulich.models.tour tour) {
		super();
		this.tour = tour;
	}

	public Itinerary() {
		super();
	}
    
    

    // Getter, Setter, Constructor
}
