package com.example.hra.service;

import com.example.hra.entity.Region;

import java.math.BigDecimal;
import java.util.List;

public interface RegionService {
    String addRegion(Region region);
    String modifyRegion(Region region);
    List<Region> getAllRegions();
    Region getRegionById(BigDecimal regionId);
    void deleteRegion(BigDecimal regionId);
}

