package com.johanekstroem.parking.Entities;
import org.geolatte.geom.G2D;
import org.geolatte.geom.Point;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ParkingSpot {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private Point<G2D> coordinate;

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
