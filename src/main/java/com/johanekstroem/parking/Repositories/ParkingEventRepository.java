package com.johanekstroem.parking.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.johanekstroem.parking.Entities.ParkingEvent;

public interface ParkingEventRepository extends CrudRepository<ParkingEvent, Long> {
 
  }
