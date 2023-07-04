package com.example.hra.service;

import com.example.hra.Entity.Location;
import com.example.hra.Repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
    public void addLocation(Location location) {
        locationRepository.save(location);
    }

    @Override
    public void updateLocation(Location location) {
        Location location1 = (Location) locationRepository.findByLocationId(location.getLocationId()).get();
        if(location1!=null)
        {
            location1.setLocationId(location.getLocationId());
            location1.setStreetAddress(location.getStreetAddress());
            location1.setCity(location.getCity());
            location1.setPostalCode(location.getPostalCode());
            location1.setStateProvince(location.getStateProvince());
            location1.setCountry(location.getCountry());
            locationRepository.save(location);
        }

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
    @Transactional
    public String deleteByLocationId(BigDecimal locationId) {
        Location location = (Location) locationRepository.findByLocationId(locationId).get();
        if(location!=null)
        {
            locationRepository.deleteByLocationId(locationId);
            return "Record deleted Successfully";
        }
        else
        {
            return "Record Not Found";
        }

    }
}

