package com.johanekstroem.parking.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.johanekstroem.parking.Entities.ParkingEvent;

public interface ParkingEventRepository extends CrudRepository<ParkingEvent, Long> {
   @Query("""
            SELECT p FROM ParkingEvent p WHERE (p.isActive) = true
            """)
    List<ParkingEvent> filterOnActiveParkingEvents();
  }
