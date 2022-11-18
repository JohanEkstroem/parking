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
  public Car addCar(@RequestBody Car car) {
    return carRepository.save(car);
  }

  @PostMapping("/customer")
  public Customer addCustomer(@RequestBody Customer customer) {
    return customerRepository.save(customer);
  }

/*   @PostMapping("/customer")
  public Customer addCustomer(@RequestBody Customer customer) {
    //validate data
      return customerRepository.save(customer);
      //return "Customer Saved.";
  } 
 */



}

// POST: http://localhost:8080/api/customer
/* {
"first_name":"Johan",
"last_name":"Ekström",
    
    "cars": [
        {
            "registrationNumber": "ABC123"
        },
        {
            
            "registrationNumber": "CBA321"
        }
        
    ]
    }
    */


    /*    {
    // POST: http://localhost:8080/api/parkingevent
     "carid":"1",
     "vilkenparkeringsplatsID":"22",
     "stop":"Date.now() + 1hr"
    
    } */

//is_ongoing = false

    /* 
     * PATCH: http://localhost:8080/api/parkingevent/
     * 
     */