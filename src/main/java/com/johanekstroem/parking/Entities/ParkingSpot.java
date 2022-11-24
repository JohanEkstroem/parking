package com.johanekstroem.parking.Entities;
import java.util.HashSet;
import java.util.Set;

import org.geolatte.geom.G2D;
import org.geolatte.geom.Point;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class ParkingSpot {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private Point<G2D> coordinate;
  @OneToMany(mappedBy = "parkingSpot")
  private Set<ParkingEvent> parkingEvent = new HashSet<>();

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Point<G2D> getCoordinate() {
    return coordinate;
  }

  public void setCoordinate(Point<G2D> coordinate) {
    this.coordinate = coordinate;
  }
  

}
