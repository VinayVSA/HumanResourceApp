package com.example.hra.Repository;

import com.example.hra.Entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Optional;

@Repository
public interface RegionRepository extends JpaRepository<Region,Integer> {

    Optional<Region> findByRegionId(BigDecimal regionId);

    void deleteByRegionId(BigDecimal regionId);
}
