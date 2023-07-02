package com.example.hra.service;

import java.math.BigDecimal;
import java.util.List;
import com.example.hra.Entity.Location;

public interface LocationService {
    String addLocation(Location location);
    String updateLocation(Location location);
    List<Location> getAllLocations();
    Location getLocationById(BigDecimal locationId);
    String deleteById(BigDecimal locationId);
}


