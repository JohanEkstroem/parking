package com.johanekstroem.parking.Controller;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.johanekstroem.parking.Entities.Car;
import com.johanekstroem.parking.Entities.ParkingEvent;
import com.johanekstroem.parking.Entities.ParkingSpot;
import com.johanekstroem.parking.Repositories.CarRepository;
import com.johanekstroem.parking.Repositories.ParkingEventRepository;
import com.johanekstroem.parking.Repositories.ParkingSpotRepository;
import com.johanekstroem.parking.Service.ValidateStopTime;

@Controller
@RequestMapping("/api")
public class ParkingEventController {

  public ParkingEventController(CarRepository carRepository, ParkingSpotRepository parkingSpotRepository,
      ParkingEventRepository parkingEventRepository, ValidateStopTime validateStopTime) {
    this.carRepository = carRepository;
    this.parkingSpotRepository = parkingSpotRepository;
    this.parkingEventRepository = parkingEventRepository;
    this.validateStopTime = validateStopTime;
  }


  CarRepository carRepository;
  ParkingSpotRepository parkingSpotRepository;
  ParkingEventRepository parkingEventRepository;
  ValidateStopTime validateStopTime;
  
  public CarRepository getCarRepository() {
    return carRepository;
  }

  public void setCarRepository(CarRepository carRepository) {
    this.carRepository = carRepository;
  }
  
  public ParkingSpotRepository getParkingSpotRepository() {
    return parkingSpotRepository;
  }
  
  public void setParkingSpotRepository(ParkingSpotRepository parkingSpotRepository) {
    this.parkingSpotRepository = parkingSpotRepository;
  }

  public ParkingEventRepository getParkingEventRepository() {
    return parkingEventRepository;
  }
  
  public void setParkingEventRepository(ParkingEventRepository parkingEventRepository) {
    this.parkingEventRepository = parkingEventRepository;
  }
  

  @PostMapping("/parkingevent")
  public ResponseEntity<ParkingEvent> startParking(@ModelAttribute("parkingevent") ParkingEvent parkingEvent) {
    var stoptime = parkingEvent.getStoptime();
    var saveParkingEvent = parkingEventRepository.save(parkingEvent);

    if (validateStopTime.isInFuture(stoptime)) {
      Long carID = parkingEvent.getCar().getId();
      var carOptional = carRepository.findById(carID);
      if (carOptional.isPresent()) {
        Car car = carOptional.get();
        car.addParkingEvent(parkingEvent);
        carRepository.save(car);
      }

      Long parkingSpotID = parkingEvent.getParkingSpot().getId();
      var parkingSpotOptional = parkingSpotRepository.findById(parkingSpotID);
      if (parkingSpotOptional.isPresent()) {
        ParkingSpot parkingSpot = parkingSpotOptional.get();
        parkingSpot.addParkingEvent(parkingEvent);
        parkingSpotRepository.save(parkingSpot);
      
         URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .buildAndExpand(saveParkingEvent.getId())
                .toUri();
                return ResponseEntity.created(location).body(saveParkingEvent);
              }
    }
    return ResponseEntity.badRequest().build();
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

/*  @GetMapping("/parkingevent")
  public String viewParkingPage(Model model){
    model.addAttribute("parkingevent",parkingEventRepository);
    return "parkingevent";
  }*/


}

 /* @PostMapping("/parkingevent")
  public ResponseEntity<ParkingEvent> startParking(@RequestBody ParkingEvent parkingEvent) {
    var stoptime = parkingEvent.getStoptime();
    var saveParkingEvent = parkingEventRepository.save(parkingEvent);

    if (validateStopTime.isInFuture(stoptime)) {
      Long carID = parkingEvent.getCar().getId();
      var carOptional = carRepository.findById(carID);
      if (carOptional.isPresent()) {
        Car car = carOptional.get();
        car.addParkingEvent(parkingEvent);
        carRepository.save(car);
      }

      Long parkingSpotID = parkingEvent.getParkingSpot().getId();
      var parkingSpotOptional = parkingSpotRepository.findById(parkingSpotID);
      if (parkingSpotOptional.isPresent()) {
        ParkingSpot parkingSpot = parkingSpotOptional.get();
        parkingSpot.addParkingEvent(parkingEvent);
        parkingSpotRepository.save(parkingSpot);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .buildAndExpand(saveParkingEvent.getId())
                .toUri();
        return ResponseEntity.created(location).body(saveParkingEvent);
      }
    }
    return ResponseEntity.badRequest().build();
  }*/