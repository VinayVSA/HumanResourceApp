package com.example.hra.controller;

import com.example.hra.Entity.Country;
import com.example.hra.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/country")
public class CountryController {

    private CountryService countryService;

    @Autowired
    public void setCountryService(CountryService countryService) {
        this.countryService = countryService;
    }

    @PostMapping("")
    public ResponseEntity<String> addCountry(@RequestBody Country country) {
        countryService.addCountry(country);
        return ResponseEntity.status(HttpStatus.CREATED).body("Record Created Successfully");
    }



    @PutMapping("")
    public ResponseEntity<String> updateCountry(@RequestBody Country country) {
        String updatedCountry = countryService.modifyCountry(country);
        return ResponseEntity.ok("Record Modified Successfully");
    }



    @GetMapping("")
    public ResponseEntity<List<Country>> getAllCountries() {
        List<Country> countries = countryService.getAllCountries();
        return ResponseEntity.ok(countries);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCountry(@PathVariable("id") String id) {
        countryService.deleteCountry(id);
        return ResponseEntity.ok().build();
    }



    @GetMapping("/{id}")
    public ResponseEntity<Country> getCountryById(@PathVariable("id") String id) {
        Country country = countryService.searchCountryById(id);
        if (country != null) {
            return ResponseEntity.ok(country);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
