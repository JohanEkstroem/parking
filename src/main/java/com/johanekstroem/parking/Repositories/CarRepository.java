package com.johanekstroem.parking.Repositories;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import com.johanekstroem.parking.Entities.Car;

public interface CarRepository extends ListCrudRepository<Car, Long> {


  @Query("""
      SELECT car FROM Car car RIGHT JOIN car.customer cust WHERE cust.userName = :username
      """)
  List<Car> findByUserName(String username);

}
