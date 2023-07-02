package com.example.hra.Repository;

import com.example.hra.Entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Optional;

@Repository
public interface LocationRepository extends JpaRepository<Location,Integer> {
    Optional<Object> findByLocationId(BigDecimal locationId);

    void deleteByLocationId(BigDecimal locationId);
}
