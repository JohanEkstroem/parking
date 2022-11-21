package com.johanekstroem.parking.Controller;

import org.geolatte.geom.G2D;
import org.geolatte.geom.Geometry;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
//import org.geolatte.geom.*;

import static org.geolatte.geom.crs.CoordinateReferenceSystems.WGS84;


import static org.geolatte.geom.builder.DSL.*;

@RestController
public class ParkingSpotController {


  @GetMapping("/parkingspot")
  public Geometry<G2D> filterPoints() {
    Geometry<G2D> area = polygon(WGS84, ring(
        g(0.0, 0.0),
        g(10.0, 0.0),
        g(10.0, 10.0),
        g(0.0, 10.0),
        g(0.0, 0.0)));
    return area;
  }
  
}
