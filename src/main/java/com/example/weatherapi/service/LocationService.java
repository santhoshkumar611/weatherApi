package com.example.weatherapi.service;

import com.example.weatherapi.model.Location;
import com.example.weatherapi.model.WeatherInfo;
import com.example.weatherapi.repository.LocationRepository;
import com.example.weatherapi.repository.WeatherInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class LocationService {

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private WeatherInfoRepository weatherInfoRepository;

    @Autowired
    private WeatherApiService weatherApiService;

    public Location saveLocation(String pincode, double latitude, double longitude) {
        Optional<Location> existingLocation = locationRepository.findByPincode(pincode);
        if (existingLocation.isPresent()) {
            return existingLocation.get();
        } else {
            Location location = new Location(pincode, latitude, longitude);
            return locationRepository.save(location);
        }
    }

    public Optional<Location> findLocationByPincode(String pincode) {
        return locationRepository.findByPincode(pincode);
    }

    public WeatherInfo getWeatherForPincode(String pincode, LocalDate forDate) {
        // First check if we have weather information in the database
        Optional<WeatherInfo> existingWeather = weatherInfoRepository.findByPincodeAndForDate(pincode, forDate);
        if (existingWeather.isPresent()) {
            return existingWeather.get();
        } else {
            // Get location by pincode
            Optional<Location> location = locationRepository.findByPincode(pincode);
            if (location.isPresent()) {
                Location loc = location.get();
                // Call external API to get weather info
                String weatherData = weatherApiService.getWeatherByLatLon(loc.getLatitude(), loc.getLongitude());
                // Parse the weather data (you will need to parse JSON here)
                String weatherDescription = "clear sky"; // Placeholder, parse the actual data
                double temperature = 25.0; // Placeholder, parse the actual data
                // Save the weather info to the database
                WeatherInfo weatherInfo = new WeatherInfo(pincode, loc.getLatitude(), loc.getLongitude(), forDate, weatherDescription, temperature);
                return weatherInfoRepository.save(weatherInfo);
            } else {
                throw new RuntimeException("Location not found for pincode " + pincode);
            }
        }
    }
}
