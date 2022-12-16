package com.johanekstroem.parking.Entities;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.geolatte.geom.G2D;
import org.geolatte.geom.Point;

@Entity
@Getter
@Setter
public class ParkingSpot {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private Point<G2D> coordinate;
  @JsonIgnore
  @OneToMany(mappedBy = "parkingSpot")
  private Set<ParkingEvent> parkingEvent = new HashSet<>();

  public void addParkingEvent(ParkingEvent parking) {
    this.parkingEvent.add(parking);
    parking.setParkingSpot(this);
  }

}
