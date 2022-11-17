package com.johanekstroem.parking.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.johanekstroem.parking.Entities.Car;
import com.johanekstroem.parking.Entities.Customer;
import com.johanekstroem.parking.Repositories.CarRepository;
import com.johanekstroem.parking.Repositories.CustomerRepository;

@RestController
@RequestMapping("api")
public class ParkingController {
  CarRepository carRepository;
  CustomerRepository customerRepository;

  public ParkingController(CarRepository carRepository, CustomerRepository customerRepository){
    this.carRepository = carRepository;
    this.customerRepository = customerRepository;
  }
  
  @GetMapping("/")
  public String demo() {
    return "Hello World";
  }

  @PostMapping("/car")
  public void addCar(@RequestBody Car car) {
    carRepository.save(car);
  }

  @PostMapping("/customer")
  public String addCustomer(@RequestBody Customer customer) {
    //validate data
      customerRepository.save(customer);
      return "Customer Saved.";
  } 




}
