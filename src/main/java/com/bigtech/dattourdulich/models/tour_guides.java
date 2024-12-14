package com.bigtech.dattourdulich.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tour_guides") // Thay "your_table_name" bằng tên bảng thực tế
public class tour_guides {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Size(min = 5, max = 20)
    @Column(name = "guide_name")
    private String guideName;

    @NotNull
    @Column(name = "contact_info")
    private String contactInfo;

    @Column(name = "experience_years")
    private int experienceYears;

    @Column(name = "status_active", columnDefinition = "TINYINT(1) DEFAULT 1")
    private boolean statusActive;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getGuideName() {
		return guideName;
	}

	public void setGuideName(String guideName) {
		this.guideName = guideName;
	}

	public String getContactInfo() {
		return contactInfo;
	}

	public void setContactInfo(String contactInfo) {
		this.contactInfo = contactInfo;
	}

	public int getExperienceYears() {
		return experienceYears;
	}

	public void setExperienceYears(int experienceYears) {
		this.experienceYears = experienceYears;
	}

	public boolean isStatusActive() {
		return statusActive;
	}

	public void setStatusActive(boolean statusActive) {
		this.statusActive = statusActive;
	}

	public tour_guides(@Size(min = 5, max = 20) String guideName, @NotNull String contactInfo, int experienceYears) {
		super();
		this.guideName = guideName;
		this.contactInfo = contactInfo;
		this.experienceYears = experienceYears;
	}

	public tour_guides() {
		super();
	}
    
    
}