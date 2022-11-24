package com.johanekstroem.parking.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class ParkingEvent {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  
  @ManyToOne
  private Car car;

  @ManyToOne
  private ParkingSpot parkingSpot;

  public ParkingSpot getParkingSpot() {
    return parkingSpot;
  }

  public void setParkingSpot(ParkingSpot parkingSpot) {
    this.parkingSpot = parkingSpot;
  }

  public Car getCar() {
    return car;
  }

  public void setCar(Car car) {
    this.car = car;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  //  What information does a parking event need?
  //  *Car
  //  *ParkingSpot
  //  *Start time
  //  *Stop time
  //  *Is parking event stopped or ongoing?

}
