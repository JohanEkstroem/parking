package com.johanekstroem.parking.Config;

import com.johanekstroem.parking.Entities.ParkingSpot;
import com.johanekstroem.parking.Repositories.ParkingSpotRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.geolatte.geom.builder.DSL.g;
import static org.geolatte.geom.builder.DSL.point;
import static org.geolatte.geom.crs.CoordinateReferenceSystems.WGS84;

@Configuration
public class ParkingSpotConfig {

  @Bean
  CommandLineRunner init(ParkingSpotRepository repo) {
    return args -> {
      var m = new ParkingSpot();
      var sdh = new ParkingSpot();
      var stp = new ParkingSpot();
      var sth = new ParkingSpot();

      m.setCoordinate(point(WGS84, g(64.7058748052286, 15.532835000884504)));
      sdh.setCoordinate(point(WGS84, g(59.37452991485647, 16.50895010823368)));
      stp.setCoordinate(point(WGS84, g(58.41563933341084, 15.6217582373094)));
      sth.setCoordinate(point(WGS84, g(59.33307196575589, 18.091222809780458)));


      repo.save(m);
      repo.save(sdh);
      repo.save(stp);
      repo.save(sth);

    };
  }
}
