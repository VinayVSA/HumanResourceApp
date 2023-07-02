package com.example.hra.service;

import com.example.hra.Entity.Location;
import com.example.hra.Repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class LocationServiceImplement implements LocationService {

    private LocationRepository locationRepository;

    @Autowired
    public void setLocationRepository(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @Override
    public String addLocation(Location location) {
        locationRepository.save(location);
        return "Record Created Successfully";
    }

    @Override
    public String updateLocation(Location location) {
        locationRepository.save(location);
        return "Record Modified Successfully";
    }

    @Override
    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }

    @Override
    public Location getLocationById(BigDecimal locationId) {
        return (Location) locationRepository.findByLocationId(locationId)
                .orElse(null);
    }

    @Override
    public String deleteById(BigDecimal locationId) {
        locationRepository.deleteByLocationId(locationId);
        return "Record deleted Successfully";
    }
}

