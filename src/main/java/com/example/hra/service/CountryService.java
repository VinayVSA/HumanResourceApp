package com.example.hra.service;

import com.example.hra.Entity.Country;

import java.util.List;

public interface CountryService {
    void addCountry(Country country);
    String modifyCountry(Country country);
    List<Country> getAllCountries();
    void deleteCountry(String id);
    Country searchCountryById(String id);
}

