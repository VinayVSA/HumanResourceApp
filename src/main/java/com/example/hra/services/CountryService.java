package com.example.hra.services;
import com.example.hra.entities.Country;

import java.util.List;

public interface CountryService {
    void addCountry(Country country);
    void modifyCountry(Country country);
    List<Country> getAllCountries();
    void deleteByCountryId(String id);
    Country searchCountryById(String id);
}

