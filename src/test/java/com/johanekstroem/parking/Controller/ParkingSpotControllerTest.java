package com.johanekstroem.parking.Controller;

import org.junit.jupiter.api.Test;
import org.locationtech.jts.geom.Coordinate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.mockito.Mockito;

import com.johanekstroem.parking.Entities.ParkingSpot;
import com.johanekstroem.parking.Repositories.ParkingSpotRepository;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

@WebMvcTest(ParkingSpotController.class)
@AutoConfigureMockMvc(addFilters = false)
public class ParkingSpotControllerTest {

    @MockBean
    ParkingSpotRepository parkingSpotRepository;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getParkingspotReturns200OK() throws Exception {
        mockMvc.perform(get("/api/parkingspot"))
                .andExpect(status().isOk());
    }

    @Test
    void getParkingspotShouldReturnListOfParkingspots() throws Exception {
        ParkingSpot parkingSpot = new ParkingSpot();

        Mockito.when(parkingSpotRepository.findAll()).thenReturn(List.of(parkingSpot));
    }

}
