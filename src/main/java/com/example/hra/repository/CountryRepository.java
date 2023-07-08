package com.example.hra.repository;
import com.example.hra.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface CountryRepository extends JpaRepository<Country,String> {
    Optional<Country> findByCountryId(String countryId);
}
