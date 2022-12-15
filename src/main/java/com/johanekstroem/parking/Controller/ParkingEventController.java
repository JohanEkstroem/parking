package com.johanekstroem.parking.Controller;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;


import com.johanekstroem.parking.Service.ParkingEventService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import com.johanekstroem.parking.Entities.ParkingEvent;
import com.johanekstroem.parking.Repositories.ParkingEventRepository;
import com.johanekstroem.parking.Service.ValidateStopTime;

@AllArgsConstructor
@Controller
@RequestMapping("/api")
public class ParkingEventController {

ValidateStopTime validateStopTime;
ParkingEventService parkingEventService;
ParkingEventRepository parkingEventRepository;

  

  @PostMapping("/parkingevent")
  public Object startParking(@ModelAttribute("parkingevent") ParkingEvent parkingEvent) {
    if (ParkingEventService.isParkingtimeOk(parkingEvent)) {
      ParkingEventService.saveParkingEvent(parkingEvent);
      parkingEventRepository.save(parkingEvent);

      return "redirect:/saved";
    }

    return "redirect:/ops";
  }



  @GetMapping("/parkingevent")
  public Iterable<ParkingEvent> getAllParkingEvents() {
    return parkingEventRepository.findAll();
  }

  @GetMapping("/parkingevent/{id}")
  public ResponseEntity<ParkingEvent> getAllParkingEventsByID(@PathVariable("id") Long id) {
    var parking = parkingEventRepository.findById(id);
    return parking.map(parkingEvent -> ResponseEntity.ok().body(parkingEvent)).orElseGet(() -> ResponseEntity.notFound().build());
  }

   @GetMapping(path = "/parkingevent", params = "filter")
    public List<ParkingEvent> filterActiveParkings(@RequestParam String filter) {
        return parkingEventRepository.filterOnActiveParkingEvents();
    }

    @PatchMapping("/parkingevent/{id}")
    public ResponseEntity<Object> updateStopTime(@PathVariable("id") Long id, @RequestBody ParkingEvent parkingEvent) {
      
      var parkingOptional = parkingEventRepository.findById(id);
      LocalDateTime newStopTime = parkingEvent.getStoptime();
      if (parkingOptional.isPresent() && validateStopTime.isInFuture(newStopTime)) {
        ParkingEvent parking = parkingOptional.get();
        var updateParkingEvent = parkingEventRepository.save(parking);
        parking.setStoptime(newStopTime);

        URI location = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(updateParkingEvent.getId())
            .toUri();

        return ResponseEntity.created(location).body(updateParkingEvent);
      }
      return ResponseEntity.badRequest().build();
    }
}
