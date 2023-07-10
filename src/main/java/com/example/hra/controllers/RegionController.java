package com.example.hra.controllers;
import com.example.hra.entities.Region;
import com.example.hra.exceptions.ValidationFailedException;
import com.example.hra.services.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;
@RestController
@RequestMapping("/api/v1/region")
public class RegionController {
    private RegionService regionService;
    @Autowired
    public void setRegionService(RegionService regionService) {
        this.regionService = regionService;
    }
    @PostMapping
    public ResponseEntity<String> addRegion(@RequestBody @Valid Region region, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            throw new ValidationFailedException("Validation Failed");;
        regionService.addRegion(region);
        return ResponseEntity.status(HttpStatus.CREATED).body("Record Created Successfully");
    }
    @PutMapping
    public ResponseEntity<String> updateRegion(@RequestBody @Valid Region region,BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            throw new ValidationFailedException("Validation Failed");;
        regionService.modifyRegion(region);
        return ResponseEntity.ok("Record Modified Successfully");
    }
    @GetMapping
    public ResponseEntity<List<Region>> getAllRegions() {
        List<Region> regions = regionService.getAllRegions();
        return ResponseEntity.ok(regions);
    }
    @GetMapping("/{regionId}")
    public ResponseEntity<Region> getRegionById(@PathVariable("regionId") BigDecimal regionId) {
        Region region = regionService.getRegionById(regionId);
        if (region != null)
            return ResponseEntity.ok(region);
        else
            return ResponseEntity.notFound().build();
    }
    @DeleteMapping("/{regionId}")
    public ResponseEntity<String> deleteRegionById(@PathVariable("regionId") BigDecimal regionId) {
        regionService.deleteRegion(regionId);
        return ResponseEntity.ok("Record deleted Successfully");
    }
}