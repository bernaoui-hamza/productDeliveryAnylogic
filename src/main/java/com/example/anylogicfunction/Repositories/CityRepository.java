package com.example.anylogicfunction.Repositories;

import com.example.anylogicfunction.Entities.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City,Integer> {
}
