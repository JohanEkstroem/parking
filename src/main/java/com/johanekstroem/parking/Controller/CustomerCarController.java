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

import com.johanekstroem.parking.Entities.Car;
import com.johanekstroem.parking.Entities.Customer;
import com.johanekstroem.parking.Repositories.CarRepository;
import com.johanekstroem.parking.Repositories.CustomerRepository;

import jakarta.servlet.http.HttpServletResponse;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
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

  @PostMapping("/customer/{id}/car")
  public Object addCarToCustomer(@PathVariable("id") Long id, HttpServletResponse httpResponse,
      @RequestBody Car customerCar) throws Exception {

    var customerByID = customerRepository.findById(id);

    if (customerByID.isPresent()) {
      Customer customer = customerByID.get();
      Car car = new Car();
      car.setRegistrationNumber(customerCar.getRegistrationNumber());
      customer.addCar(car);
      carRepository.save(car);

      URI location = ServletUriComponentsBuilder
          .fromCurrentRequest()
          .path("/{id/car}")
          .buildAndExpand(customer.getId())
          .toUri();

      httpResponse.sendRedirect("/saved");
      return ResponseEntity.created(location).body(customerByID);

    }
    httpResponse.sendRedirect("/ops");
    return null;
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
