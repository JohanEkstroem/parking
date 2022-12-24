package com.johanekstroem.parking.Controller;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import com.johanekstroem.parking.Entities.Car;
import com.johanekstroem.parking.Repositories.CarRepository;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@WebAppConfiguration
@AutoConfigureMockMvc(addFilters = false)
public class CustomerCarControllerTest {

  @MockBean
  CarRepository carRepo;

  @Autowired
  private MockMvc mockMvc;

  @Test
  void getCarsReturnStatus200OK() throws Exception {
    mockMvc.perform(get("/api/cars"))
        .andExpect(status().isOk());
  }

  @Test
  void getAllCarsShouldReturnListOfCars() throws Exception {
    Car car = new Car();
    car.setRegistrationNumber("ABC123");

    Mockito.when(carRepo.findAll()).thenReturn(List.of(car));

    mockMvc.perform(get("/api/cars"))
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$[0].registrationNumber").value("ABC123"));

  }
}
