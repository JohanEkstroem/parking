package com.johanekstroem.parking.Entities.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class ParkingController {
  @RequestMapping("/demo")
  public String demo() {
    return "Hello World";
  }

}
