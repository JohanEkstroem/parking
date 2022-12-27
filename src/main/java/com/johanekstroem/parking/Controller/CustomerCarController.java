package com.johanekstroem.parking.Controller;

import java.net.URI;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

import com.johanekstroem.parking.Entities.Car;
import com.johanekstroem.parking.Entities.Customer;
import com.johanekstroem.parking.Repositories.CarRepository;
import com.johanekstroem.parking.Repositories.CustomerRepository;
@AllArgsConstructor
@RestController
@RequestMapping("api")
public class CustomerCarController {
  
  CarRepository carRepository;
  CustomerRepository customerRepository;
  
  @PostMapping("/customer")
  public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {
    var newCustomer = customerRepository.save(customer);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .buildAndExpand(newCustomer.getId())
                .toUri();

        return ResponseEntity.created(location).body(newCustomer);
  }

  @GetMapping("/customer")
  public Iterable<Customer> getAllCustomers() {
    return customerRepository.findAll();
  }

  @GetMapping("/customer/{id}")
  public ResponseEntity<Customer> getAllCustomerByID(@PathVariable("id") Long id) {
    var customer = customerRepository.findById(id);
    if (customer.isPresent()) {
      return ResponseEntity.ok().body(customer.get());
    }
    return ResponseEntity.notFound().build();
  }

  @PostMapping("/customer/{username}/car")
  public Object addCarToCustomer(@PathVariable("username") String username, @RequestBody Car userCar) throws Exception {

    var customerByID = customerRepository.findByUserName(username);
    
    if (customerByID.isPresent()) {
      Customer customer = customerByID.get();
      Car car = new Car();
      car.setRegistrationNumber(userCar.getRegistrationNumber());
      customer.addCar(car);
      carRepository.save(car);

      URI location = ServletUriComponentsBuilder
          .fromCurrentRequest()
          .path("/{username/car}")
          .buildAndExpand(customer.getId())
          .toUri();

      ResponseEntity.created(location).body(customerByID);
      return new ResponseEntity<>("hello", HttpStatus.CREATED);

    } else {
      return new ResponseEntity<>("No user", HttpStatus.UNAUTHORIZED);
    }
  }
  

  @GetMapping("/cars")
  public Iterable<Car> getAllCars() {
    return carRepository.findAll();
  }

  
  @GetMapping("/cars/{id}")
  public ResponseEntity<Car> getAllCarsByID(@PathVariable("id") Long id) {
    var car = carRepository.findById(id);
    if (car.isPresent()) {
      return ResponseEntity.ok().body(car.get());
    }
    return ResponseEntity.notFound().build();
  }

}
