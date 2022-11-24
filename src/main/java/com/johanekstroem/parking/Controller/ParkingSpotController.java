package com.johanekstroem.parking.Controller;

import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.johanekstroem.parking.Entities.ParkingSpot;
import com.johanekstroem.parking.Repositories.ParkingSpotRepository;

@RestController
@RequestMapping("api")
public class ParkingSpotController {

  public ParkingSpotController(ParkingSpotRepository parkingSpotRepository) {
    this.parkingSpotRepository = parkingSpotRepository;
  }

  ParkingSpotRepository parkingSpotRepository;

  public ParkingSpotRepository getParkingSpotRepository() {
    return parkingSpotRepository;
  }

  public void setParkingSpotRepository(ParkingSpotRepository parkingSpotRepository) {
    this.parkingSpotRepository = parkingSpotRepository;
  }

  @PostMapping("/parkingspot")
  public ParkingSpot points(@RequestBody ParkingSpot parking) {
    return parkingSpotRepository.save(parking);
  }

  
  @GetMapping("/parkingspot/{id}")
  public Optional<ParkingSpot> getAllCustomerByID(@PathVariable("id") Long id) {
    var parkingspot = parkingSpotRepository.findById(id);
    if (parkingspot.isPresent()) {
      return parkingspot;
    }
    return null;
  }

  @GetMapping("/parkingspot")
  public Iterable<ParkingSpot> getAllParkingSpots() {
    return parkingSpotRepository.findAll();
  }
  
}
