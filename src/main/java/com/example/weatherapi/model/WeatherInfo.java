package com.example.weatherapi.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class WeatherInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String pincode;
    private double latitude;
    private double longitude;
    private LocalDate forDate;
    private String weatherDescription;
    private double temperature;

    // Default constructor
    public WeatherInfo() {}

    // Parameterized constructor
    public WeatherInfo(String pincode, double latitude, double longitude, LocalDate forDate, String weatherDescription, double temperature) {
        this.pincode = pincode;
        this.latitude = latitude;
        this.longitude = longitude;
        this.forDate = forDate;
        this.weatherDescription = weatherDescription;
        this.temperature = temperature;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public LocalDate getForDate() {
        return forDate;
    }

    public void setForDate(LocalDate forDate) {
        this.forDate = forDate;
    }

    public String getWeatherDescription() {
        return weatherDescription;
    }

    public void setWeatherDescription(String weatherDescription) {
        this.weatherDescription = weatherDescription;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }
}
