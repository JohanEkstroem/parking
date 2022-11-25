package com.johanekstroem.parking.Controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.johanekstroem.parking.Entities.Car;
import com.johanekstroem.parking.Entities.ParkingEvent;
import com.johanekstroem.parking.Entities.ParkingSpot;
import com.johanekstroem.parking.Repositories.CarRepository;
import com.johanekstroem.parking.Repositories.ParkingEventRepository;
import com.johanekstroem.parking.Repositories.ParkingSpotRepository;
import com.johanekstroem.parking.Service.ValidateStopTime;

@RestController
@RequestMapping("api")
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
  public ParkingEvent startParking(@RequestBody ParkingEvent parkingEvent) {
    var stoptime = parkingEvent.getStoptime();

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
      return parkingEventRepository.save(parkingEvent);
    }
      
  }
    return null;
  }
  
  @GetMapping("/parkingevent")
  public Iterable<ParkingEvent> getAllParkingEvents() {
    return parkingEventRepository.findAll();
  }

  @GetMapping("/parkingevent/{id}")
  public Optional<ParkingEvent> getAllParkingEventsByID(@PathVariable("id") Long id) {
    var parking = parkingEventRepository.findById(id);
    if (parking.isPresent()) {
      return parking;
    }
    return null;
  }

   @GetMapping(path = "/parkingevent", params = "filter")
    public List<ParkingEvent> filterActiveParkings(@RequestParam String filter) {
        return parkingEventRepository.filterOnActiveParkingEvents();
    }

    @PatchMapping("/parkingevent/{id}")
    public ParkingEvent updateStopTime(@PathVariable("id") Long id, @RequestBody ParkingEvent parkingEvent) {
      
      var parkingOptional = parkingEventRepository.findById(id);
      LocalDateTime newStopTime = parkingEvent.getStoptime();
        if (parkingOptional.isPresent() && validateStopTime.isInFuture(newStopTime)) {
        ParkingEvent parking = parkingOptional.get();
        parking.setStoptime(newStopTime);
        return parkingEventRepository.save(parking);
      } else
        return null;
    }
}
