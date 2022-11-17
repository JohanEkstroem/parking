package com.johanekstroem.parking.Entities;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Customer {
  
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    @JsonProperty("firstName")
    String firstName;
    @JsonProperty("lastName")
    String lastName;
    

    
@OneToMany(mappedBy ="customer")
    private Set<Car> cars;

  public long getId() {
    return id;
  }
  public void setId(long id) {
    this.id = id;
  }
  public String getFirstName() {
    return firstName;
  }
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }
  public String getLastName() {
    return lastName;
  }
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }
  public Set<Car> getCars() {
    return cars;
  }
  public void setCars(Set<Car> cars) {
    this.cars = cars;
  }

}
