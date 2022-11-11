package com.johanekstroem.parking.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.johanekstroem.parking.Entities.Car;
import com.johanekstroem.parking.Entities.Person;
import com.johanekstroem.parking.Repositories.CarRepository;
import com.johanekstroem.parking.Repositories.PersonRepository;

@RestController
@RequestMapping("api")
public class ParkingController {
  CarRepository carRepository;
  PersonRepository personRepository;

  public ParkingController(CarRepository carRepository, PersonRepository personRepository){
    this.carRepository = carRepository;
    this.personRepository = personRepository;
  }
  
  @GetMapping("/")
  public String demo() {
    return "Hello World";
  }

  @PostMapping("/car")
  public void addCar(@RequestBody Car car) {
    carRepository.save(car);
  }

  @PostMapping("/person")
  public String addPerson(@RequestBody Person person) {
    //validate data
      personRepository.save(person);
      return "Person Saved.";
  } 




}
