package com.example.hra.service;

import com.example.hra.Entity.Country;
import com.example.hra.Repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryServiceImplement implements CountryService {

    private  CountryRepository countryRepository;

    @Autowired
    public void CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public void addCountry(Country country) {
        countryRepository.save(country);
    }

    @Override
    public void modifyCountry(Country country) {
        countryRepository.save(country);

    }

    @Override
    public List<Country> getAllCountries() {
        return countryRepository.findAll();
    }

    @Override
    public void deleteCountry(String id) {
        countryRepository.deleteById(id);
    }

    @Override
    public Country searchCountryById(String id) {
        Optional<Country> country = countryRepository.findById(id);
        return country.orElse(null);
    }
}

