package com.johanekstroem.parking.Repositories;

import org.springframework.data.repository.CrudRepository;

import com.johanekstroem.parking.Entities.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long>{
  
}
