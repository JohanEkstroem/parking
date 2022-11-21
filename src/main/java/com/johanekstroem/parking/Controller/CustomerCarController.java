package com.johanekstroem.parking.Controller;

import java.util.List;

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
public class CustomerCarController {
  
  CarRepository carRepository;
  CustomerRepository customerRepository;

  public CustomerCarController(CarRepository carRepository, CustomerRepository customerRepository){
    this.carRepository = carRepository;
    this.customerRepository = customerRepository;
  }  
  
  @PostMapping("/customer")
  public Customer addCustomer(@RequestBody Customer customer) {
    //TODO Validate data
    return customerRepository.save(customer);
  }

  @GetMapping("/customer")
  public Iterable<Customer> getAllCustomers() {

    return customerRepository.findAll();
  }
  //Find Customer with ID add add a new car
}
