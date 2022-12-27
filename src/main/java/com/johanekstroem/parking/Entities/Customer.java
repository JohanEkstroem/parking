package com.johanekstroem.parking.Entities;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Customer {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String userName;
  private String firstName;
  private String lastName;

  @OneToMany(mappedBy = "customer")
  private Set<Car> cars = new HashSet<>();

  public void addCar(Car car) {
    this.cars.add(car);
    car.setCustomer(this);
  }
}
