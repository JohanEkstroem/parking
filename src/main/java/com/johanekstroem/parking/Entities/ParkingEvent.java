package com.johanekstroem.parking.Entities;

import java.time.LocalDateTime;

import org.hibernate.annotations.ColumnDefault;
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

  @Override
  public String toString() {
    return "ParkingEvent{" +
            "created=" + created +
            ", updated=" + updated +
            ", stoptime=" + stoptime +
            ", isActive=" + isActive +
            ", car=" + car +
            ", parkingSpot=" + parkingSpot +
            '}';
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  
  @CreationTimestamp
  @Column(name= "created", nullable = false, updatable = false)
  private LocalDateTime created;

  @UpdateTimestamp
  private LocalDateTime updated;

  @Column(nullable = false)
  private LocalDateTime stoptime;

  public ParkingEvent() {
  }

  public ParkingEvent(LocalDateTime updated, LocalDateTime stoptime, Car car, ParkingSpot parkingSpot) {
    this.updated = updated;
    this.stoptime = stoptime;
    this.car = car;
    this.parkingSpot = parkingSpot;
  }

  @ColumnDefault("true")
  private Boolean isActive = true;
  
  
  public Boolean getIsActive() {
    return isActive;
  }

  public void setIsActive(Boolean isActive) {
    this.isActive = isActive;
  }

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
}
