package com.johanekstroem.parking.Service;

import com.johanekstroem.parking.Entities.Car;
import com.johanekstroem.parking.Entities.ParkingEvent;
import com.johanekstroem.parking.Entities.ParkingSpot;
import com.johanekstroem.parking.Exceptions.CarNotFoundException;
import com.johanekstroem.parking.Repositories.CarRepository;
import com.johanekstroem.parking.Repositories.ParkingEventRepository;
import com.johanekstroem.parking.Repositories.ParkingSpotRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ParkingEventServiceImp implements ParkingEventService{
  CarRepository carRepository;
  ParkingSpotRepository parkingSpotRepository;
  ParkingEventRepository parkingEventRepository;

  public ParkingEventServiceImp(CarRepository carRepository, ParkingSpotRepository parkingSpotRepository, ParkingEventRepository parkingEventRepository) {
    this.carRepository = carRepository;
    this.parkingSpotRepository = parkingSpotRepository;
    this.parkingEventRepository = parkingEventRepository;
  }

  @Override
  public List<ParkingEvent> getAllParkingEvents() {
    return parkingEventRepository.findAll();
  }

  @Override
  public Optional<ParkingEvent> getOneParkingEventByID(Long ParkingEventId) {
    return parkingEventRepository.findById(ParkingEventId);
  }


  @Override
  public boolean updateStopTime(Long ParkingEventId, ParkingEvent parkingEvent) {

    var parkingOptional = parkingEventRepository.findById(ParkingEventId);
    LocalDateTime newStopTime = parkingEvent.getStoptime();

    if (parkingOptional.isPresent() && isInFuture(newStopTime)) {
      ParkingEvent parking = parkingOptional.get();
      parkingEventRepository.save(parking);
      parking.setStoptime(newStopTime);
      return true;

    }else return false;
  }

  @Override
  public boolean startParking(ParkingEvent parkingEvent) {
    if (isParkingtimeOk(parkingEvent)) {
      saveParkingEvent(parkingEvent);
      parkingEventRepository.save(parkingEvent);

      return true;
    }else
      return false;
  }

  @Override
  public List<ParkingEvent> filterOnActiveParkingEvents(Long carId, Boolean isActive) {
     var car = parkingEventRepository.findById(carId);
     if(car.isPresent()){
       return parkingEventRepository.findByIsActive(isActive);
     } else throw new CarNotFoundException(carId);
  }


  public boolean isParkingtimeOk(ParkingEvent parkingEvent) {
    var stoptime = parkingEvent.getStoptime();
    return (isInFuture(stoptime));
  }

  public void saveParkingEvent(ParkingEvent parkingEvent)
  {
    Long carID = parkingEvent.getCar().getId();
    var carOptional = carRepository.findById(carID);
    if (carOptional.isPresent()) {
      saveCar(parkingEvent, carOptional.get());
      saveParkingSpot(parkingEvent);
    }
  }

  public void saveParkingSpot(ParkingEvent parkingEvent) {
    Long parkingSpotID = parkingEvent.getParkingSpot().getId();
    var parkingSpotOptional = parkingSpotRepository.findById(parkingSpotID);
    if (parkingSpotOptional.isPresent()) {
      ParkingSpot parkingSpot = parkingSpotOptional.get();
      parkingSpot.addParkingEvent(parkingEvent);
      parkingSpotRepository.save(parkingSpot);
    }
  }

  public void saveCar(ParkingEvent parkingEvent, Car car) {
    car.addParkingEvent(parkingEvent);
    carRepository.save(car);
  }
  public boolean isInFuture(LocalDateTime stoptime){
    return stoptime.isAfter(LocalDateTime.now());    }

}
