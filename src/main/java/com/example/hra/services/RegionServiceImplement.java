package com.example.hra.services;
import com.example.hra.entities.Region;
import com.example.hra.exceptions.RegionNotFoundException;
import com.example.hra.repositories.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
        return "Record Created Successfully";}
    @Override
    public String modifyRegion(Region region) {
        regionRepository.save(region);
        return "Record Modified Successfully";}
    @Override
    public List<Region> getAllRegions() {
        return regionRepository.findAll();}
    @Override
    public Region getRegionById(BigDecimal regionId) {
      return regionRepository.findByRegionId(regionId).orElseThrow(()->new RegionNotFoundException("Region Not Found"));}
    @Override
    @Transactional
    public void deleteRegion(BigDecimal regionId) {
        regionRepository.findByRegionId(regionId).orElseThrow(()->new RegionNotFoundException("Region Not Found"));
        try {regionRepository.deleteRegionByRegionId(regionId);}
        catch (RuntimeException ex){throw new RegionNotFoundException("Cannot delete or update a parent row: a foreign key constraint fails");}
        }
}

