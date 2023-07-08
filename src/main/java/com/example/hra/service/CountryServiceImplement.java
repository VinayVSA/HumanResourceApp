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
        Country country1 = countryRepository.findByCountryId(country.getCountryId()).orElseThrow(()->new CountryNotFoundException("Country Not Found"));
        country1.setCountryId(country.getCountryId());
        country1.setCountryName(country.getCountryName());
        country1.setRegion(country.getRegion());
        countryRepository.save(country1);}
    @Override
    public List<Country> getAllCountries() {
        return countryRepository.findAll();
    }
    @Override
    public void deleteByCountryId(String countryId) {
       countryRepository.findByCountryId(countryId).orElseThrow(()->new CountryNotFoundException("Country Not Found"));
        try {countryRepository.deleteById(countryId);}
        catch (Exception c)
        {throw new CountryNotFoundException("Cannot delete or update a parent row: a foreign key constraint");}}
    @Override
    public Country searchCountryById(String countryId) {
    return countryRepository.findByCountryId(countryId).orElseThrow(()->new CountryNotFoundException("Country Not Found"));}
}

