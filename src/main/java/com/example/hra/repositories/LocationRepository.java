package com.example.hra.repositories;
import com.example.hra.entities.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.math.BigDecimal;
import java.util.Optional;
@Repository
public interface LocationRepository extends JpaRepository<Location,Integer> {
    Optional<Location> findByLocationId(BigDecimal locationId);
    void deleteByLocationId(BigDecimal locationId);
}
