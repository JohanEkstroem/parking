package com.johanekstroem.parking.Controller;

import java.net.URI;
import java.util.List;

import com.johanekstroem.parking.Service.ParkingEventService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.HttpStatus;

import com.johanekstroem.parking.Entities.ParkingEvent;

@AllArgsConstructor
@Controller
@RequestMapping("/api")
public class ParkingEventController {

  ParkingEventService parkingEventService;

  @GetMapping("/parkingevent")
  @ResponseBody
  public List<ParkingEvent> getAllParkingEvents() {
    return parkingEventService.getAllParkingEvents();
  }

  @GetMapping("/parkingevent/{ParkingEventId}")
  @ResponseBody
  public ResponseEntity<ParkingEvent> getAllParkingEventsByID(@PathVariable Long ParkingEventId) {
    var result = parkingEventService.getOneParkingEventByID(ParkingEventId);
    return result.map(parkingEvent -> ResponseEntity.ok().body(parkingEvent))
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PostMapping("/parkingevent")
  public Object startParking(@RequestBody ParkingEvent parkingEvent) {
    if (parkingEventService.startParking(parkingEvent)) {
      return new ResponseEntity<>("parking created", HttpStatus.CREATED);
    } else {
      return new ResponseEntity<>("No user", HttpStatus.UNAUTHORIZED);
    }

  }

  @GetMapping(path = "/parkingevent/cars/{carId}/{isActive}")
  @ResponseBody
  public List<ParkingEvent> filterActiveParkings(@PathVariable Long carId, @PathVariable Boolean isActive) {
    return parkingEventService.filterOnActiveParkingEvents(carId, isActive);
  }

  @PatchMapping("/parkingevent/{ParkingEventId}")
  @ResponseBody
  public ResponseEntity<Object> updateStopTime(@PathVariable Long ParkingEventId,
      @RequestBody ParkingEvent parkingEvent) {
    if (parkingEventService.updateStopTime(ParkingEventId, parkingEvent)) {

      URI location = ServletUriComponentsBuilder
          .fromCurrentRequest()
          .path("/{id}")
          .buildAndExpand(parkingEventService.getOneParkingEventByID(ParkingEventId))
          .toUri();

      return ResponseEntity.created(location).body(parkingEventService.getOneParkingEventByID(ParkingEventId));
    }
    return ResponseEntity.badRequest().build();
  }
}