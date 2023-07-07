package com.example.hra.service;

import com.example.hra.entity.Country;
import com.example.hra.exception.CountryNotFoundException;
import com.example.hra.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryServiceImplement implements CountryService {

    private  CountryRepository countryRepository;

    @Autowired
    public void CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public void addCountry(Country country)
    {
        countryRepository.save(country);
    }

    @Override
    public void modifyCountry(Country country) {
        Country country1 = countryRepository.findByCountryId(country.getCountryId());
        if(country1==null)
        {
            throw new CountryNotFoundException("Country Not Found");
        }
        country1.setCountryId(country.getCountryId());
        country1.setCountryName(country.getCountryName());
        country1.setRegion(country.getRegion());
        countryRepository.save(country1);
    }

    @Override
    public List<Country> getAllCountries() {
        List<Country> countries = countryRepository.findAll();
        if(countries.isEmpty())
        {
            throw new CountryNotFoundException("Countries Not Found");
        }
        return countryRepository.findAll();
    }
    @Override
    public void deleteCountry(String countryId) {
        Country country = countryRepository.findByCountryId(countryId);
        if(country==null)
        {
            throw new CountryNotFoundException("Country Not Found");
        }
        try {
            countryRepository.deleteById(countryId);
        }
        catch (Exception c)
        {
            throw new CountryNotFoundException("Cannot delete or update a parent row: a foreign key constraint");
        }

    }
    @Override
    public Country searchCountryById(String countryId) {
        Country country = countryRepository.findByCountryId(countryId);
        if(country==null)
        {
            throw new CountryNotFoundException("Country Not Found");
        }
        else
            return country;
    }
}

