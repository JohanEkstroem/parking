package com.johanekstroem.parking.Entities;


import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Car {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)

  private Long id;
  private String registrationNumber;
  
  @ManyToOne
  @JsonIgnore
  private Customer customer;
  
  @OneToMany(mappedBy = "car")
  private Set<ParkingEvent> parkingEvent = new HashSet<>();
  
@JsonIgnore
  public Set<ParkingEvent> getParkingEvent() {
    return parkingEvent;
  }

  public void addParkingEvent(ParkingEvent parking) {
    this.parkingEvent.add(parking);
    parking.setCar(this);
  }
  
}
