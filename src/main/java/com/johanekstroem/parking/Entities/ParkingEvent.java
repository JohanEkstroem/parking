package com.johanekstroem.parking.Entities;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
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
  
  @CreationTimestamp
  @Column(name= "created", nullable = false, updatable = false)
  private LocalDateTime created;

  @UpdateTimestamp
  private LocalDateTime updated;

  private LocalDateTime stoptime;

  private Boolean isOngoing;
  
  
  @ManyToOne
  private Car car;

  @ManyToOne
  private ParkingSpot parkingSpot;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public LocalDateTime getCreated() {
    return created;
  }

  public void setCreated(LocalDateTime created) {
    this.created = created;
  }

  public LocalDateTime getUpdated() {
    return updated;
  }

  public void setUpdated(LocalDateTime updated) {
    this.updated = updated;
  }

  public LocalDateTime getStoptime() {
    return stoptime;
  }

  public void setStoptime(LocalDateTime stoptime) {
    this.stoptime = stoptime;
  }

  public Boolean getIsOngoing() {
    return isOngoing;
  }

  public void setIsOngoing(Boolean isOngoing) {
    this.isOngoing = isOngoing;
  }

  public Car getCar() {
    return car;
  }

  public void setCar(Car car) {
    this.car = car;
  }

  public ParkingSpot getParkingSpot() {
    return parkingSpot;
  }

  public void setParkingSpot(ParkingSpot parkingSpot) {
    this.parkingSpot = parkingSpot;
  }

  

  //  What information does a parking event need?
  //  *Car
  //  *ParkingSpot
  //  *Start time
  //  *Stop time
  //  *Is parking event stopped or ongoing?

}
