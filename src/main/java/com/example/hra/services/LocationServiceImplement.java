package com.example.hra.services;
import com.example.hra.entities.Location;
import com.example.hra.exceptions.LocationNotFoundException;
import com.example.hra.repositories.LocationRepository;
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
        locationRepository.save(location);
    }
    @Override
    public List<Location> getAllLocations() {
        return locationRepository.findAll();}
    @Override
    public Location getLocationById(BigDecimal locationId) {
        return locationRepository.findByLocationId(locationId).orElseThrow(()->new LocationNotFoundException("Location Not Found"));}
    @Override
    @Transactional
    public String deleteByLocationId(BigDecimal locationId) {
        if(locationRepository.findByLocationId(locationId).isPresent()) {
            locationRepository.deleteByLocationId(locationId);
            return "Record deleted Successfully";
        }else{
            throw new LocationNotFoundException("Location Not Found");
        }
    }
}