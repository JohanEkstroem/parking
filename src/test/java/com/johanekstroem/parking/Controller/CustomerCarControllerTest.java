package com.johanekstroem.parking.Controller;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.johanekstroem.parking.Entities.Car;
import com.johanekstroem.parking.Repositories.CarRepository;
import static org.assertj.core.api.Assertions.*;


public class CustomerCarControllerTest {
  @Test
  void testGetAllCars() {
    CarRepository repo = Mockito.mock(CarRepository.class);
    Car car = new Car();
    car.setRegistrationNumber("ABC123");
    Mockito.when(repo.findAll()).thenReturn(List.of(car));

    CustomerCarController carController = new CustomerCarController(repo, null);
    
    var result = carController.getAllCars();

    assertThat(result).hasSize(1);
  }

  @Test
  void testGetAllCustomers() {

  }
}
