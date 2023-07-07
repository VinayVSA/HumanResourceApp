package com.example.hra.service;

import com.example.hra.entity.Region;
import com.example.hra.exception.RegionNotFoundException;
import com.example.hra.repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Service
public class RegionServiceImplement implements RegionService {

    private RegionRepository regionRepository;

    @Autowired
    public void setRegionRepository(RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
    }

    @Override
    public String addRegion(Region region) {
        regionRepository.save(region);
        return "Record Created Successfully";
    }
    @Override
    public String modifyRegion(Region region) {
        Region region1 = regionRepository.findByRegionId(region.getRegionId());
        if(region1==null)
        {
            throw new RegionNotFoundException("Region Not Exist");
        }
        region1.setRegionId(region.getRegionId());
        region1.setRegionName(region.getRegionName());
        regionRepository.save(region1);
        return "Record Modified Successfully";
    }

    @Override
    public List<Region> getAllRegions() {
        List<Region> regions = regionRepository.findAll();
        if(regions.isEmpty())
        {
            throw new RegionNotFoundException("No Regions Found");
        }
        return regions;
    }

    @Override
    public Region getRegionById(BigDecimal regionId) {
        Region region = regionRepository.findByRegionId(regionId);
        if(region==null)
        {
            throw new RegionNotFoundException("Region Not Found");
        }
        return region;
    }

    @Override
    @Transactional
    public void deleteRegion(BigDecimal regionId) {
        Region region = regionRepository.findByRegionId(regionId);
        if(region!=null)
        {
            regionRepository.deleteRegionByRegionId(regionId);
        }
        else
        {
            throw new RegionNotFoundException("Region Not Found");
        }

    }
}

