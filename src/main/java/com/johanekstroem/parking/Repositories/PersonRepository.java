package com.johanekstroem.parking.Repositories;

import org.springframework.data.repository.CrudRepository;

import com.johanekstroem.parking.Entities.Person;

public interface PersonRepository extends CrudRepository<Person, Long>{
  
}
