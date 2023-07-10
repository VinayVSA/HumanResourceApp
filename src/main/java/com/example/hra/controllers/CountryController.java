package com.example.hra.controllers;
import com.example.hra.entities.Country;
import com.example.hra.exceptions.ValidationFailedException;
import com.example.hra.services.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
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
    public ResponseEntity<String> addCountry(@RequestBody @Valid Country country, BindingResult bindingResult) {
        if(bindingResult.hasErrors())
            throw new ValidationFailedException("Validation Failed");
        countryService.addCountry(country);
        return ResponseEntity.status(HttpStatus.CREATED).body("Record Created Successfully");
    }
    @PutMapping("")
    public ResponseEntity<String> updateCountry(@RequestBody@Valid Country country,BindingResult bindingResult) {
        if(bindingResult.hasErrors())
            throw new ValidationFailedException("Validation failed");
        countryService.modifyCountry(country);
        return ResponseEntity.ok("Record Modified Successfully");
    }
    @GetMapping("")
    public ResponseEntity<List<Country>> getAllCountries() {
        List<Country> countries = countryService.getAllCountries();
        return ResponseEntity.ok(countries);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCountry(@PathVariable("id") String countryId) {
        countryService.deleteByCountryId(countryId);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Country> getCountryById(@PathVariable("id") String id) {
        Country country = countryService.searchCountryById(id);
        if (country != null)
            return ResponseEntity.ok(country);
        else
            return ResponseEntity.notFound().build();
    }
}
