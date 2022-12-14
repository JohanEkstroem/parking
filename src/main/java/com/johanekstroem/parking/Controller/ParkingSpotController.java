package com.johanekstroem.parking.Controller;

import java.net.URI;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.johanekstroem.parking.Entities.ParkingSpot;
import com.johanekstroem.parking.Repositories.ParkingSpotRepository;

@AllArgsConstructor
@RestController
@RequestMapping("api")
public class ParkingSpotController {

  ParkingSpotRepository parkingSpotRepository;


  @PostMapping("/parkingspot")
  public ResponseEntity<ParkingSpot> points(@RequestBody ParkingSpot parking) {
    var parkingSpot = parkingSpotRepository.save(parking);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .buildAndExpand(parkingSpot.getId())
                .toUri();

        return ResponseEntity.created(location).body(parkingSpot);
  }

  
  @GetMapping("/parkingspot/{id}")
  public ResponseEntity<ParkingSpot> getAllCustomerByID(@PathVariable("id") Long id) {
    var parkingspot = parkingSpotRepository.findById(id);
    if (parkingspot.isPresent()) {
      return ResponseEntity.ok().body(parkingspot.get());
    }
    return ResponseEntity.notFound().build();
  }

  @GetMapping("/parkingspot")
  public Iterable<ParkingSpot> getAllParkingSpots() {
    return parkingSpotRepository.findAll();
  }
  
}
