package com.johanekstroem.parking.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Car {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  long id;
  String registrationNumber;
  
  
  @ManyToOne
  @JoinColumn(name="person_id")
  private Customer person;


  public long getId() {
    return id;
  }


  public void setId(long id) {
    this.id = id;
  }


  public String getRegistrationNumber() {
    return registrationNumber;
  }


  public void setRegistrationNumber(String registrationNumber) {
    this.registrationNumber = registrationNumber;
  }


  public Customer getPerson() {
    return person;
  }


  public void setPerson(Customer person) {
    this.person = person;
  }

}
