package com.cg.test.model;

import javax.persistence.*;

@Entity
@Table(name = "cities")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long city_id;
    private String city_name;
    private double acreage;
    private double population;
    private double gdp;
    private String description;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    public City() {
    }

    public City(Long city_id, String city_name, double acreage, double population, double gdp, String description, Country country) {
        this.city_id = city_id;
        this.city_name = city_name;
        this.acreage = acreage;
        this.population = population;
        this.gdp = gdp;
        this.description = description;
        this.country = country;
    }



    public City(String city_name, double acreage, double population, double gdp, String description, Country country) {
        this.city_name = city_name;
        this.acreage = acreage;
        this.population = population;
        this.gdp = gdp;
        this.description = description;
        this.country = country;
    }

    public Long getCity_id() {
        return city_id;
    }

    public void setCity_id(Long city_id) {
        this.city_id = city_id;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public double getAcreage() {
        return acreage;
    }

    public void setAcreage(double acreage) {
        this.acreage = acreage;
    }

    public double getPopulation() {
        return population;
    }

    public void setPopulation(double population) {
        this.population = population;
    }

    public double getGdp() {
        return gdp;
    }

    public void setGdp(double GDP) {
        this.gdp = GDP;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
