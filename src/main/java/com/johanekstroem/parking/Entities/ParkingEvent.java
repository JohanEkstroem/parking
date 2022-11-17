package com.johanekstroem.parking.Entities;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ParkingEvent {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  //  What information does a parking event need?
  //  *Car
  //  *ParkingSpot
  //  *Start time
  //  *Stop time
  //  *Is parking event stopped or ongoing?

}
