package com.johanekstroem.parking.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
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

@RestController
@RequestMapping("api")
public class ParkingEventController {

  public ParkingEventController(CarRepository carRepository, ParkingSpotRepository parkingSpotRepository,
      ParkingEventRepository parkingEventRepository) {
    this.carRepository = carRepository;
    this.parkingSpotRepository = parkingSpotRepository;
    this.parkingEventRepository = parkingEventRepository;
  }


  CarRepository carRepository;
  ParkingSpotRepository parkingSpotRepository;
  ParkingEventRepository parkingEventRepository;
  
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
    Long carID = parkingEvent.getCar().getId();
    var carOptional = carRepository.findById(carID);
    if (carOptional.isPresent()) {
      Car bil = carOptional.get();
      bil.addParkingEvent(parkingEvent);
      carRepository.save(bil);
    }

    Long parkingSpotID = parkingEvent.getParkingSpot().getId();
    var parkingSpotOptional = parkingSpotRepository.findById(parkingSpotID);
    if (parkingSpotOptional.isPresent()) {
      ParkingSpot parkingSpot = parkingSpotOptional.get();
      parkingSpot.addParkingEvent(parkingEvent);
      parkingSpotRepository.save(parkingSpot);
    }

    return parkingEventRepository.save(parkingEvent);

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


}
