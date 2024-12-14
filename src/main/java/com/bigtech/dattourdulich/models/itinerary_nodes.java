package com.bigtech.dattourdulich.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class itinerary_nodes {
	   	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int id;

	    // Các thuộc tính khác
	    private String location;
	    private String time_node;
	    private int day_of_tour;
	    
	    // Liên kết đến tour
	    @ManyToOne
	    @JoinColumn(name = "itineraries_id", nullable = false)
	    private Itinerary itinerary;

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getLocation() {
			return location;
		}

		public void setLocation(String location) {
			this.location = location;
		}

		public String getTime_node() {
			return time_node;
		}

		public void setTime_node(String time_node) {
			this.time_node = time_node;
		}

		public int getDay_of_tour() {
			return day_of_tour;
		}

		public void setDay_of_tour(int day_of_tour) {
			this.day_of_tour = day_of_tour;
		}

		public Itinerary getItinerary() {
			return itinerary;
		}

		public void setItinerary(Itinerary itinerary) {
			this.itinerary = itinerary;
		}

		public itinerary_nodes(String location, String time_node, int day_of_tour, Itinerary itinerary) {
			super();
			this.location = location;
			this.time_node = time_node;
			this.day_of_tour = day_of_tour;
			this.itinerary = itinerary;
		}

		public itinerary_nodes() {
			super();
		}
		
		@Override
	    public String toString() {
	        return "itinerary_nodes{" +
	               "id=" + id +
	               ", location='" + location + '\'' +
	               ", time_node='" + time_node + '\'' +
	               ", day_of_tour=" + day_of_tour +
	               ", itinerary=" + itinerary.getId() +
	               '}';
	    }
	    
	    
}
