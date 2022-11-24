package com.johanekstroem.parking.Controller;

import org.springframework.web.bind.annotation.GetMapping;
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
  
  @GetMapping("/addCustomer")
  public Customer addCustomerss() {
    Customer customer = new Customer();
    customer.setFirstName("Johan");
    customer.setLastName("Ekström");
    
    Car car1 = new Car();
    car1.setRegistrationNumber("ABC123");
    carRepository.save(car1);

    Car car2 = new Car();
    car2.setRegistrationNumber("CBA321");
    carRepository.save(car2);
    
    customer.addCar(car1);
    customer.addCar(car2);
     
    return customerRepository.save(customer);
  }

}
