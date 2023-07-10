package com.example.hra.services;

import java.math.BigDecimal;
import java.util.List;
import com.example.hra.entities.Location;

public interface LocationService {
    void addLocation(Location location);
    void updateLocation(Location location);
    List<Location> getAllLocations();
    Location getLocationById(BigDecimal locationId);
    String deleteByLocationId(BigDecimal locationId);
}


