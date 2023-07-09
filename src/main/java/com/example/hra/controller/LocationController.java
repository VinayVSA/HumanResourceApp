package com.example.hra.controller;
import com.example.hra.entity.Location;
import com.example.hra.exception.ValidationFailedException;
import com.example.hra.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;
@RestController
@RequestMapping("/api/v1/location")
public class LocationController {
    private LocationService locationService;
    @Autowired
    public void setLocationService(LocationService locationService) {
        this.locationService = locationService;
    }
    @PostMapping
    public ResponseEntity<String> addLocation(@RequestBody @Valid Location location, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            throw new ValidationFailedException("Validation Failed");
        locationService.addLocation(location);
        return ResponseEntity.status(HttpStatus.CREATED).body("Record Created Successfully");
    }

    @PutMapping
    public ResponseEntity<String> updateLocation(@RequestBody @Valid Location location,BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            throw new ValidationFailedException("Validation Failed");
        locationService.updateLocation(location);
        return ResponseEntity.ok("Record Modified Successfully");
    }
    @GetMapping
    public ResponseEntity<List<Location>> getAllLocations() {
        List<Location> locations = locationService.getAllLocations();
        return ResponseEntity.ok(locations);
    }
    @GetMapping("/{locationId}")
    public ResponseEntity<Location> getLocationById(@PathVariable("locationId") BigDecimal locationId) {
        Location location = locationService.getLocationById(locationId);
        if (location != null)
            return ResponseEntity.ok(location);
        else
            return ResponseEntity.notFound().build();
    }
    @DeleteMapping("/{locationId}")
    public ResponseEntity<String> deleteLocationById(@PathVariable("locationId") BigDecimal locationId) {
        locationService.deleteByLocationId(locationId);
        return ResponseEntity.ok("Record deleted Successfully");
    }
}
