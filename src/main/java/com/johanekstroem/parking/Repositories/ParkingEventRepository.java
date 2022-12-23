package com.johanekstroem.parking.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.johanekstroem.parking.Entities.ParkingEvent;


import java.util.List;


public interface ParkingEventRepository extends JpaRepository<ParkingEvent, Long> {

  List<ParkingEvent> findByIsActive (Boolean isActive);

}