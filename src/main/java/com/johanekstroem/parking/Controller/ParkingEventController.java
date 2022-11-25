package com.johanekstroem.parking.Controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
  

  //Creates a new parking event
  @PostMapping("/parkingevent")
  public ParkingEvent startParking(@RequestBody ParkingEvent parkingEvent) {
    // Plocka ut bil-ID från JSON
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
    // Leta upp bilen i databasen som har det ID
    // Lägga till ett parkeringsevent på den bilen
    // Spara bilen till databasen

    // Plocka parkeringsplats-ID från JSON
    // Leta upp parkeringsplatsen i databasen som har det ID
    // Lägga till ett parkeringsevent på den parkeringsplatsen
    // Spara parkeringsplatsen till databasen
    
    // Spara parkeringstillfället i databasen
    //return ResponseEntity.ok(HttpStatus.OK);

  }

}
