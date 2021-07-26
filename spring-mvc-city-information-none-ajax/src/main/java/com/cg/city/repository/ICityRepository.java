package com.cg.city.repository;

import com.cg.city.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ICityRepository extends JpaRepository<City,Long> {

    @Query("select c from City c order by c.city_id desc")
    Iterable<City> findAllByOrderByCity_idDesc();
}
