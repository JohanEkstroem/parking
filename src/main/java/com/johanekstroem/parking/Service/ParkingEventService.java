package com.johanekstroem.parking.Service;

import com.johanekstroem.parking.Entities.ParkingEvent;


import java.util.List;
import java.util.Optional;

public interface ParkingEventService {

  List<ParkingEvent> getAllParkingEvents();
  Optional<ParkingEvent> getOneParkingEventByID(Long ParkingEventId);

  boolean updateStopTime(Long ParkingEventId, ParkingEvent parkingEvent);
  boolean startParking(ParkingEvent parkingEvent);

  List<ParkingEvent>filterOnActiveParkingEvents(Long carId, Boolean isActive);

}
