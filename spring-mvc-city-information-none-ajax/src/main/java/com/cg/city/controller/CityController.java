package com.cg.city.controller;


import com.cg.city.model.City;
import com.cg.city.model.Country;
import com.cg.city.service.city.ICityService;
import com.cg.city.service.country.ICountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class CityController {

    @Autowired
    private ICityService cityService;

    @Autowired
    private ICountryService countryService;

    @ModelAttribute("countries")
    public Iterable<Country> countries(){
        return countryService.findAll();
    }

    @GetMapping("/cities")
    public ModelAndView listCities() {
        Iterable<City> cities = cityService.findAllByOrderByCity_idDesc();
        ModelAndView modelAndView = new ModelAndView("/city/list");
        modelAndView.addObject("cities", cities);
        return modelAndView;
    }

    @GetMapping("/create-city")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("/city/create");
        modelAndView.addObject("city", new City());
        return modelAndView;
    }

    @PostMapping("/create-city")
    public ModelAndView saveCustomer(@ModelAttribute("city") City city) {
        cityService.save(city);
//        ModelAndView modelAndView = new ModelAndView("/customer/list");
//        modelAndView.addObject("customer", new Customer());
//        modelAndView.addObject("message", "New customer created successfully");
//        return modelAndView;
        Iterable<City> cities = cityService.findAllByOrderByCity_idDesc();
        ModelAndView modelAndView = new ModelAndView("/city/list");
        modelAndView.addObject("msg_create","Create");
        modelAndView.addObject("cities", cities);
        return modelAndView;
    }

    @GetMapping("/edit-city/{id}")
    public ModelAndView showEditForm(@PathVariable Long id) {
        Optional<City> city = cityService.findById(id);
        if (city.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("/city/edit");
            modelAndView.addObject("city", city.get());
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("/error-404");
            return modelAndView;
        }
    }

    @PostMapping("/edit-city")
    public ModelAndView updateCustomer(@ModelAttribute("city") City city) {
        cityService.save(city);
//        ModelAndView modelAndView = new ModelAndView("/customer/list");
//        modelAndView.addObject("customer", customer);
//        modelAndView.addObject("message", "Customer updated successfully");
//        return modelAndView;
        Iterable<City> cities = cityService.findAllByOrderByCity_idDesc();
        ModelAndView modelAndView = new ModelAndView("/city/list");
        modelAndView.addObject("msg_edit","Edit");
        modelAndView.addObject("cities", cities);
        return modelAndView;
    }

    @GetMapping("/delete-city/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id) {
        Optional<City> city = cityService.findById(id);
        if (city.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("/city/delete");
            modelAndView.addObject("city", city.get());
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("/error-404");
            return modelAndView;
        }
    }

//    @PostMapping("/delete-city")
//    public String deleteCustomer(@ModelAttribute("city") City city) {
//        cityService.remove(city.getCity_id());
//        return "redirect:cities";
//    }

    @PostMapping("/delete-city")
    public ModelAndView deleteCustomer(@ModelAttribute("city") City city) {
        cityService.remove(city.getCity_id());
        Iterable<City> cities = cityService.findAllByOrderByCity_idDesc();
        ModelAndView modelAndView = new ModelAndView("/city/list");
        modelAndView.addObject("msg_delete","Delete");
        modelAndView.addObject("cities", cities);
        return modelAndView;
    }

    @GetMapping("/view/{id}")
    public ModelAndView showViewCity(@PathVariable Long id){
        Optional<City> city = cityService.findById(id);
        ModelAndView modelAndView = new ModelAndView("/city/view");
        modelAndView.addObject("city",city.get());
        return modelAndView;
    }
}
