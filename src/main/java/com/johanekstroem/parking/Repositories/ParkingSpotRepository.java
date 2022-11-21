package com.johanekstroem.parking.Repositories;

import org.springframework.data.repository.CrudRepository;
import com.johanekstroem.parking.Entities.ParkingSpot;

public interface ParkingSpotRepository extends CrudRepository<ParkingSpot,Long>{
  
}
