package com.example.hra.service;
import com.example.hra.entity.Location;
import com.example.hra.exception.LocationNotFoundException;
import com.example.hra.repository.LocationRepository;
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
       Location location1=(Location)locationRepository.findByLocationId(location.getLocationId()).orElseThrow(()->new LocationNotFoundException("Location Not Found"));
        location1.setLocationId(location.getLocationId());
        location1.setStreetAddress(location.getStreetAddress());
        location1.setCity(location.getCity());
        location1.setPostalCode(location.getPostalCode());
        location1.setStateProvince(location.getStateProvince());
        location1.setCountry(location.getCountry());
        locationRepository.save(location);
    }
    @Override
    public List<Location> getAllLocations() {
        List<Location> locations = locationRepository.findAll();
        if(locations.isEmpty())
            throw new LocationNotFoundException("Locations Not Found");
        return locations;
    }
    @Override
    public Location getLocationById(BigDecimal locationId) {
        return (Location) locationRepository.findByLocationId(locationId).orElseThrow(()->new LocationNotFoundException("Location Not Found"));
    }
    @Override
    @Transactional
    public String deleteByLocationId(BigDecimal locationId) {
        locationRepository.findByLocationId(locationId).orElseThrow(()->new LocationNotFoundException("Location Not Found"));
        locationRepository.deleteByLocationId(locationId);
        return "Record deleted Successfully";
    }
}