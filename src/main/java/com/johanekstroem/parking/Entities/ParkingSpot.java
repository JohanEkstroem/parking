package com.johanekstroem.parking.Entities;
import java.util.HashSet;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;
import org.geolatte.geom.G2D;
import org.geolatte.geom.Point;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
@Getter
@Setter
public class ParkingSpot {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private Point<G2D> coordinate;
  @OneToMany(mappedBy = "parkingSpot")
  private Set<ParkingEvent> parkingEvent = new HashSet<>();

  public void addParkingEvent(ParkingEvent parking) {
    this.parkingEvent.add(parking);
    parking.setParkingSpot(this);
  }

}
