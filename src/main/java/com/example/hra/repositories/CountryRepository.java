package com.example.hra.repositories;
import com.example.hra.entities.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface CountryRepository extends JpaRepository<Country,String> {
    Optional<Country> findByCountryId(String countryId);
}
