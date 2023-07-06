package com.example.hra.service;

import com.example.hra.entity.Country;

import java.util.List;

public interface CountryService {
    void addCountry(Country country);
    void modifyCountry(Country country);
    List<Country> getAllCountries();
    void deleteCountry(String id);
    Country searchCountryById(String id);
}

