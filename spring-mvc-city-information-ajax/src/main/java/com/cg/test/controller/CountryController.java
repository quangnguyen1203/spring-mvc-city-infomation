package com.cg.test.controller;


import com.cg.test.model.City;
import com.cg.test.model.Country;
import com.cg.test.service.city.CityService;
import com.cg.test.service.city.ICityService;
import com.cg.test.service.country.CountryService;
import com.cg.test.service.country.ICountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class CountryController {

    @Autowired
    private ICountryService countryService;

    @Autowired
    private ICityService customerService;

    @GetMapping("/countries")
    public ModelAndView listProvinces() {
        Iterable<Country> countries = countryService.findAll();
        ModelAndView modelAndView = new ModelAndView("/country/list");
        modelAndView.addObject("countries", countries);
        return modelAndView;
    }

    @GetMapping("/create-country")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("/country/create");
        modelAndView.addObject("country", new Country());
        return modelAndView;
    }

    @PostMapping("/create-country")
    public ModelAndView saveProvince(@ModelAttribute("country") Country country) {
        countryService.save(country);

        ModelAndView modelAndView = new ModelAndView("/country/create");
        modelAndView.addObject("country", new Country());
        modelAndView.addObject("message", "New country created successfully");
        return modelAndView;
    }

    @GetMapping("/edit-country/{id}")
    public ModelAndView showEditForm(@PathVariable Long id) {
        Optional<Country> country = countryService.findById(id);
        if (country.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("/country/edit");
            modelAndView.addObject("country", country.get());
            return modelAndView;

        } else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping("/edit-country")
    public ModelAndView updateProvince(@ModelAttribute("country") Country country) {
        countryService.save(country);
        ModelAndView modelAndView = new ModelAndView("/country/edit");
        modelAndView.addObject("country", country);
        modelAndView.addObject("message", "Country updated successfully");
        return modelAndView;
    }

    @GetMapping("/delete-country/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id) {
        Optional<Country> country = countryService.findById(id);
        if (country.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("/country/delete");
            modelAndView.addObject("country", country.get());
            return modelAndView;

        } else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping("/delete-country")
    public String deleteProvince(@ModelAttribute("country") Country country) {
        countryService.remove(country.getCountry_id());
        return "redirect:countries";
    }

//    @GetMapping("/view-country/{id}")
//    public ModelAndView viewProvince(@PathVariable("id") Long id){
//        Optional<Country> countryOptional = countryService.findById(id);
//        if(!countryOptional.isPresent()){
//            return new ModelAndView("/error.404");
//        }
//
//        Iterable<Customer> customers = customerService.findAllByCountry(countryOptional.get());
//
//        ModelAndView modelAndView = new ModelAndView("/country/view");
//        modelAndView.addObject("country", countryOptional.get());
//        modelAndView.addObject("customers", customers);
//        return modelAndView;
//    }
}