package com.johanekstroem.parking.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.MediaType;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.johanekstroem.parking.Entities.Car;
import com.johanekstroem.parking.Entities.Customer;
import com.johanekstroem.parking.Repositories.CarRepository;
import com.johanekstroem.parking.Repositories.CustomerRepository;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CustomerCarController.class)
@AutoConfigureMockMvc(addFilters = false)
public class CustomerControllerTest {

    @MockBean
    CustomerRepository customerRepo;

    @MockBean
    CarRepository carRepo;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getCustomerShouldReturnListOfCustomers() throws Exception {
        var customer = new Customer();
        customer.setFirstName("Haeju");
        customer.setLastName("Java");
        customer.setId(1L);
        Mockito.when(customerRepo.findAll()).thenReturn(List.of(customer));

        mockMvc.perform(get("/api/customer"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].firstName").value("Haeju"))
                .andExpect(jsonPath("$[0].id").value("1"));
    }

    @Test
    void postCustomerShouldCreateCustomer() throws Exception {
        var customer = new Customer();
        customer.setFirstName("Elona");
        customer.setLastName("Muska");
        customer.setId(1L);

        Mockito.when(customerRepo.save(Mockito.any(Customer.class))).thenReturn(customer);

        mockMvc.perform(post("/api/customer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(customer))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists());
    }

    @Test
    void getCustomerByIdShouldReturnRightCustomer() throws Exception {
        var customer = new Customer();
        customer.setFirstName("Haeju");
        customer.setLastName("Java");
        customer.setId(1L);

        var customer2 = new Customer();
        customer2.setFirstName("Elona");
        customer2.setLastName("Muska");
        customer2.setId(2L);

        Mockito.when(customerRepo.findById(1L)).thenReturn(Optional.of(customer));
        Mockito.when(customerRepo.findById(2L)).thenReturn(Optional.of(customer2));

        mockMvc.perform(get("/api/customer/2"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.firstName").value("Elona"))
                .andExpect(jsonPath("$.id").value("2"));

    }



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


    @Test
    void postCustomerCarToCustomerShouldCreateCustomersCar() throws Exception {
        var customer = new Customer();
        customer.setFirstName("Elona");
        customer.setLastName("Muska");
        customer.setUserName("twitterCEO");
        customer.setId(1L);

        var car = new Car();
        car.setRegistrationNumber("Taslaa005");
        car.setId(1L);

        Mockito.when(customerRepo.findByUserName("twitterCEO")).thenReturn(Optional.of(customer));
        Mockito.when(carRepo.save(Mockito.any(Car.class))).thenReturn(car);

        mockMvc.perform(post("/api/customer/" + "twitterCEO" + "/car")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(car))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

    }

    @Test
    void getCarByIdReturnCarWithRequestedId() throws Exception {
        Car car = new Car();
        car.setRegistrationNumber("ABC123");
        car.setId(1L);

        Optional<Car> optionalCar = Optional.of(car);

        Mockito.when(carRepo.findById(ArgumentMatchers.any())).thenReturn(optionalCar);

        mockMvc.perform(get("/api/car/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.registrationNumber").value("ABC123"))
                .andExpect(jsonPath("$.id").value("1"));
    }





    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
