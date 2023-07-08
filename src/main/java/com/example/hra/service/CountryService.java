package com.example.hra.service;
import com.example.hra.entity.Country;

import java.util.List;
import java.util.Optional;

public interface CountryService {
    void addCountry(Country country);
    void modifyCountry(Country country);
    List<Country> getAllCountries();
    void deleteByCountryId(String id);
    Country searchCountryById(String id);
}

