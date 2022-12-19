package com.johanekstroem.parking.Exceptions;

  public class CarNotFoundException extends RuntimeException {

    public CarNotFoundException(Long carId){super("Car with id '" + carId + "' does not exist");}

  }

