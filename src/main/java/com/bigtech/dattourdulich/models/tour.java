package com.bigtech.dattourdulich.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tours") // Tên bảng thực tế trong cơ sở dữ liệu
public class tour {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Size(min = 1, max = 100) // Đặt giới hạn cho tên tour
    @Column(name = "tourname")
    private String tourName;

    @Size(min = 1, max = 500) // Đặt giới hạn cho mô tả tour
    @Column(name = "descriptions")
    private String descriptions;

    @NotNull
    @Column(name = "duration")
    private int duration; // Thời gian tour, có thể tính theo ngày

    @NotNull
    @Column(name = "price")
    private double price; // Giá tour

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTourName() {
        return tourName;
    }

    public void setTourName(String tourName) {
        this.tourName = tourName;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public tour(@Size(min = 3, max = 100) String tourName, @Size(min = 10, max = 500) String descriptions, 
                @NotNull int duration, @NotNull double price) {
        super();
        this.tourName = tourName;
        this.descriptions = descriptions;
        this.duration = duration;
        this.price = price;
    }

    public tour() {
        super();
    }
}
