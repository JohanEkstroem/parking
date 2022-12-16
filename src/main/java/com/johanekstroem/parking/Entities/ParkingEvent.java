package com.johanekstroem.parking.Entities;

import java.time.LocalDateTime;

import lombok.*;
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
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ParkingEvent {

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


  @ColumnDefault("true")
  private Boolean isActive = true;

  @ManyToOne
  private Car car;

  @ManyToOne
  private ParkingSpot parkingSpot;

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

}
