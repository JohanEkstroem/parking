package com.johanekstroem.parking.Service;


import com.johanekstroem.parking.Entities.Car;
import com.johanekstroem.parking.Entities.ParkingEvent;
import com.johanekstroem.parking.Entities.ParkingSpot;
import com.johanekstroem.parking.Repositories.CarRepository;
import com.johanekstroem.parking.Repositories.ParkingEventRepository;
import com.johanekstroem.parking.Repositories.ParkingSpotRepository;

import org.junit.jupiter.api.*;
import static org.mockito.Mockito.*;


import java.time.LocalDateTime;
import java.util.*;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

class ParkingEventServiceTest {

  private ParkingEventRepository parkingEventRepository;

  private ParkingEventService parkingEventService;

  @BeforeEach
  void setup(){
    CarRepository carRepository = mock(CarRepository.class);
    ParkingSpotRepository parkingSpotRepository = mock(ParkingSpotRepository.class);
    this.parkingEventRepository = mock(ParkingEventRepository.class);

    this.parkingEventService = new ParkingEventServiceImp(carRepository, parkingSpotRepository,parkingEventRepository);

    }

  @Test
  void getAllParkingEvents() {


    var createTime= (LocalDateTime.parse("2022-12-14T10:34"));
    var stopTime= (LocalDateTime.parse("2022-12-14T18:34"));
    Car car = new Car();
    car.setRegistrationNumber("ABC123");
    ParkingSpot parkingSpot = null;

    when(parkingEventRepository.findAll()).thenReturn(Arrays.asList(
            new ParkingEvent(1L,createTime,createTime,stopTime, true, car, parkingSpot),
            new ParkingEvent(2L,createTime,createTime,stopTime, true, car, parkingSpot)
    ));

    List<ParkingEvent> result =  parkingEventService.getAllParkingEvents();

    assertThat(result).hasSize(2);

  }

  @Test
  void getAllParkingEventsByID() {


    var createTime= (LocalDateTime.parse("2022-12-14T10:34"));
    var stopTime= (LocalDateTime.parse("2022-12-14T18:34"));
    Car car = new Car();
    car.setRegistrationNumber("ABC123");
    ParkingSpot parkingSpot = null;

    when(parkingEventRepository.findById(1L)).thenReturn(Optional.of(
             new ParkingEvent(1L,createTime,createTime,stopTime, true, car, parkingSpot)));

    Optional<ParkingEvent> result1 =  parkingEventService.getOneParkingEventByID(1L);

  Assertions.assertEquals(1L, result1.get().getId().longValue());

  }




  @Test
  void updateStopTime() {
    var createTime= (LocalDateTime.parse("2022-12-14T10:34"));
    var stopTime= (LocalDateTime.parse("2022-12-14T18:34"));
    Car car = new Car();
    car.setRegistrationNumber("ABC123");
    ParkingSpot parkingSpot = null;

    ParkingEvent parking;
    when(parkingEventRepository.findById(1L)).thenReturn(Optional.of(
           parking= new ParkingEvent (1L,createTime,createTime,stopTime, true, car, parkingSpot)));

    var updateTime= (LocalDateTime.parse("2022-12-14T20:34"));
    parking.setStoptime(updateTime);


    Assertions.assertEquals(LocalDateTime.parse("2022-12-14T20:34"), parking.getStoptime());
  }

  @Test
  void startParking() {
    var createTime= (LocalDateTime.parse("2022-12-14T10:34"));
    var stopTime= (LocalDateTime.parse("2022-12-14T18:34"));
    Car car = new Car();
    car.setRegistrationNumber("ABC123");
    ParkingSpot parkingSpot = null;

    ParkingEvent parking;
    when(parkingEventRepository.findById(1L)).thenReturn(Optional.of(
            parking= new ParkingEvent (1L,createTime,createTime,stopTime, true, car, parkingSpot)));

    Assertions.assertEquals(LocalDateTime.parse("2022-12-14T10:34"), parking.getCreated());
  }

  @Test
  void filterOnActiveParkingEvents() {
    var createTime= (LocalDateTime.parse("2022-12-14T10:34"));
    var stopTime= (LocalDateTime.parse("2022-12-14T18:34"));
    var expiredTime= (LocalDateTime.parse("2022-12-14T10:34"));
    Car car1 = new Car();
    car1.setRegistrationNumber("Danger013");
    car1.setId(1L);
    Car car2 = new Car();
    car2.setRegistrationNumber("Tre113");
    car2.setId(2L);
    ParkingSpot parkingSpot = null;

    Boolean activeParking = true;
    when(parkingEventRepository.findByIsActive(activeParking)).thenReturn(List.of(
            new ParkingEvent(1L, createTime, createTime, stopTime, true, car1, parkingSpot)
    ));

    Boolean endedParking = false;
    when(parkingEventRepository.findByIsActive(endedParking)).thenReturn(List.of(
            new ParkingEvent(2L, createTime, createTime, expiredTime, false, car2, parkingSpot)
    ));

    when(parkingEventRepository.findById(1L)).thenReturn(Optional.of(
            new ParkingEvent(1L,createTime,createTime,stopTime, true, car1, parkingSpot)));

    when(parkingEventRepository.findById(2L)).thenReturn(Optional.of(
            new ParkingEvent(2L,createTime,createTime,stopTime, true, car2, parkingSpot)));

    List<ParkingEvent> result1 =  parkingEventService.filterOnActiveParkingEvents(1L, true);
    List<ParkingEvent> result2 =  parkingEventService.filterOnActiveParkingEvents(2L, false);

   Assertions.assertEquals(true, result1.get(0).getIsActive());
    Assertions.assertEquals(false, result2.get(0).getIsActive());


  }
}