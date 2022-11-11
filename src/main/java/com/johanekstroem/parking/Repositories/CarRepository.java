package com.johanekstroem.parking.Repositories;

import org.springframework.data.repository.CrudRepository;
import com.johanekstroem.parking.Entities.Car;

public interface CarRepository extends CrudRepository<Car, Long>{
  
}
