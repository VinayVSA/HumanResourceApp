package com.example.hra;
import com.example.hra.entities.Country;
import com.example.hra.entities.Job;
import com.example.hra.entities.Location;
import com.example.hra.entities.Region;
import com.example.hra.repositories.CountryRepository;
import com.example.hra.repositories.JobRepository;
import com.example.hra.repositories.LocationRepository;
import com.example.hra.repositories.RegionRepository;
import com.example.hra.services.CountryService;
import com.example.hra.services.RegionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class HumanResourceAppTest {
    private JobRepository jobRepository;
    @Autowired
    public void setJobRepository(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }
    private RegionRepository regionRepository;
    @Autowired
    public void setRegionRepository(RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
    }
    private CountryRepository countryRepository;
    @Autowired
    public void setCountryRepository(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    private RegionService regionService;
    @Autowired
    public void setRegionService(RegionService regionService) {
        this.regionService = regionService;
    }
    private CountryService countryService;
    @Autowired
    public void setCountryService(CountryService countryService) {
        this.countryService = countryService;
    }
    private LocationRepository locationRepository;
    @Autowired
    public void setLocationRepository(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @Test
    public void testGetByJobId()
    {
        String jobId = "7ym6HQOTQb";
        Job job = new Job("7ym6HQOTQb","Manager",BigDecimal.valueOf(1200),new BigDecimal(1600));
        Assertions.assertEquals(job,jobRepository.findByJobId(jobId).get());
    }
    @Test
    public void testUpdateRegion()
    {
        Assertions.assertEquals(new Region(BigDecimal.valueOf(6),"EastIndia"),regionRepository.save(new Region(BigDecimal.valueOf(6),"EastIndia")));
    }
    @Test
    public void testGetRegionById()
    {
        Assertions.assertEquals(new Region(BigDecimal.valueOf(30),"Asia"),regionRepository.findByRegionId(BigDecimal.valueOf(30)).get());
    }
    @Test
    public void testUpdateJob()
    {
        Assertions.assertEquals(new Job("SA_REP","Sales Represent",BigDecimal.valueOf(6000),BigDecimal.valueOf(12008)),
                jobRepository.save(new Job("SA_REP","Sales Represent",BigDecimal.valueOf(6000),BigDecimal.valueOf(12008))));
    }
    @Test
    public void testGetLocationById()
    {
        Assertions.assertEquals(new Location(BigDecimal.valueOf(7),"Kokapet","500002","Hyderabad","Telangana",countryService.searchCountryById("JP"))
                ,locationRepository.findByLocationId(new BigDecimal(7)).get());
    }
    @Test
    public void testModifyCountry() {
        Assertions.assertEquals(new Country("AR","Argentina",regionService.getRegionById(BigDecimal.valueOf(20)))
                ,countryRepository.save(new Country("AR","Argentina",regionService.getRegionById(BigDecimal.valueOf(20)))));
    }
    @Test
    public void testGetAllRegions()
    {
        List<Region> regionsList = new ArrayList<>();
        regionsList.add(new Region(BigDecimal.valueOf(1),"USSR"));
        regionsList.add(new Region(BigDecimal.valueOf(6),"EastIndia"));
        regionsList.add(new Region(BigDecimal.valueOf(10),"Europe"));
        regionsList.add(new Region(BigDecimal.valueOf(17),"Pacific"));
        regionsList.add(new Region(BigDecimal.valueOf(20),"Americas"));
        regionsList.add(new Region(BigDecimal.valueOf(24),"Pan India"));
        regionsList.add(new Region(BigDecimal.valueOf(30),"Asia"));
        regionsList.add(new Region(BigDecimal.valueOf(40),"Oceania"));
        regionsList.add(new Region(BigDecimal.valueOf(50),"Africa"));
        Assertions.assertEquals(regionsList,regionService.getAllRegions());
    }
    @Test
    public void testUpdateLocation()
    {
        Assertions.assertEquals(new Location(BigDecimal.valueOf(1400),"churchilRoad","26192","England","UK",countryService.searchCountryById("US"))
                ,new Location(BigDecimal.valueOf(1400),"churchilRoad","26192","England","UK",countryService.searchCountryById("US")));
    }
    @Test
    public void testModifyJobHistory()
    {

    }
}