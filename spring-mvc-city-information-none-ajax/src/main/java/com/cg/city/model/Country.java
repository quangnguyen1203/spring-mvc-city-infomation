package com.cg.city.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "countries")
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long country_id;
    private String country_name;

    @OneToMany(targetEntity = City.class)
    private List<City> cities;


    public Country() {
    }

    public Country(Long country_id) {
        this.country_id = country_id;
    }

    public Country(String country_name, List<City> cities) {
        this.country_name = country_name;
        this.cities = cities;
    }

    public Country(Long country_id, String country_name, List<City> cities) {
        this.country_id = country_id;
        this.country_name = country_name;
       this.cities = cities;
    }

    public Long getCountry_id() {
        return country_id;
    }

    public void setCountry_id(Long country_id) {
        this.country_id = country_id;
    }

    public String getCountry_name() {
        return country_name;
    }

    public void setCountry_name(String country_name) {
        this.country_name = country_name;
    }

    public List<City> getCustomers() {
        return cities;
    }

    public void setCustomers(List<City> cities) {
        this.cities = cities;
    }
}
