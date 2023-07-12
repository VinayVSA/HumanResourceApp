package com.example.hra.services;
import com.example.hra.entities.Country;
import com.example.hra.exceptions.CountryNotFoundException;
import com.example.hra.repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class CountryServiceImplement implements CountryService {
    private  CountryRepository countryRepository;
    @Autowired
    public void setCountryRepository(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public void addCountry(Country country)
    {
        countryRepository.save(country);
    }
    @Override
    public void modifyCountry(Country country) {
        countryRepository.save(country);}
    @Override
    public List<Country> getAllCountries() {
        return countryRepository.findAll();
    }
    @Override
    public void deleteByCountryId(String countryId) {
       if(countryRepository.findByCountryId(countryId).isPresent()) {
           try {
               countryRepository.deleteById(countryId);
           } catch (Exception c) {
               throw new CountryNotFoundException("Cannot delete or update a parent row: a foreign key constraint");
           }
       }else{
           throw new CountryNotFoundException("Country Not Found");
       }
    }
    @Override
    public Country searchCountryById(String countryId) {
    return countryRepository.findByCountryId(countryId).orElseThrow(()->new CountryNotFoundException("Country Not Found"));}
}

