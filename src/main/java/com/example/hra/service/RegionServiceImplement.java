package com.example.hra.service;

import com.example.hra.Entity.Region;
import com.example.hra.Repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

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
        Region region1 = regionRepository.findByRegionId(region.getRegionId()).get();

        if(region1!=null)
        {
            region1.setRegionId(region.getRegionId());
            region1.setRegionName(region.getRegionName());
            regionRepository.save(region1);
        }


        return "Record Modified Successfully";
    }

    @Override
    public List<Region> getAllRegions() {
        return regionRepository.findAll();
    }

    @Override
    public Region getRegionById(BigDecimal regionId) {
        Optional<Region> region = regionRepository.findByRegionId(regionId);
        return region.orElse(null);
    }

    @Override
    @Transactional
    public void deleteRegion(BigDecimal regionId) {
        Region region = regionRepository.findByRegionId(regionId).get();
        if(region!=null)
        {
            regionRepository.deleteRegionByRegionId(regionId);
        }

    }
}

