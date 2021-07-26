package com.cg.test.controller;

import com.cg.test.model.City;
import com.cg.test.model.Country;
import com.cg.test.service.city.ICityService;
import com.cg.test.service.country.ICountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@RestController
@RequestMapping("/cities")
public class CityController {

    @Autowired
    private ICityService cityService;

    @Autowired
    private ICountryService countryService;

    @ModelAttribute("countries")
    public Iterable<Country> countries(){
        return countryService.findAll();
    }

    @GetMapping
    public ModelAndView listCity(){
        ModelAndView modelAndView = new ModelAndView("/city/list");
        modelAndView.addObject("cities",cityService.findAll());
        return modelAndView;
    }


    @PostMapping("/create-city")
    public ResponseEntity<City> createCity(@RequestBody City city){
        Optional<Country> country = countryService.findById(city.getCountry().getCountry_id());
        city.getCountry().setCountry_name(country.get().getCountry_name());
        return new ResponseEntity<>(cityService.save(city), HttpStatus.CREATED);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<City> editCity(@RequestBody City city,@PathVariable Long id){
        city.setCity_id(id); ;
        city.getCountry().setCountry_name(countryService.findById(city.getCountry().getCountry_id()).get().getCountry_name());
        return new ResponseEntity<>(cityService.save(city),HttpStatus.OK);
    }

    @GetMapping("/edit-city/{id}")
    public ResponseEntity<City> cityResponseEntity(@PathVariable Long id){
        City city = cityService.findById(id).get();
        return new ResponseEntity<>(city,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<City> deleteProduct(@PathVariable Long id) {
        Optional<City> cityOptional = cityService.findById(id);
        if (!cityOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        cityService.remove(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}

