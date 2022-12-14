package com.johanekstroem.parking.Controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
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

        System.out.println(customer);
        mockMvc.perform(post("/api/customer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(customer))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists());
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
