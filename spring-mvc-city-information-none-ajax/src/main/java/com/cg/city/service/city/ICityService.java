package com.cg.city.service.city;

import com.cg.city.model.City;
import com.cg.city.service.IGeneralService;

public interface ICityService extends IGeneralService<City> {
    Iterable<City> findAllByOrderByCity_idDesc();
}
