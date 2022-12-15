package com.johanekstroem.parking.Service;

import com.johanekstroem.parking.Entities.Car;
import com.johanekstroem.parking.Entities.ParkingEvent;
import com.johanekstroem.parking.Entities.ParkingSpot;
import com.johanekstroem.parking.Repositories.CarRepository;
import com.johanekstroem.parking.Repositories.ParkingSpotRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Service
@Getter
@Setter
public class ParkingEventService {
   static ValidateStopTime validateStopTime;
    static CarRepository carRepository;
 static ParkingSpotRepository parkingSpotRepository;

    public ParkingEventService(ValidateStopTime validateStopTime, CarRepository carRepository, ParkingSpotRepository parkingSpotRepository) {
        ParkingEventService.validateStopTime = validateStopTime;
        ParkingEventService.carRepository = carRepository;
        ParkingEventService.parkingSpotRepository = parkingSpotRepository;
    }

    public static boolean isParkingtimeOk(ParkingEvent parkingEvent) {
        var stoptime = parkingEvent.getStoptime();
        return (validateStopTime.isInFuture(stoptime));
    }

    public static void saveParkingEvent(ParkingEvent parkingEvent)
    {
        Long carID = parkingEvent.getCar().getId();
        var carOptional = carRepository.findById(carID);
        if (carOptional.isPresent()) {
            saveCar(parkingEvent, carOptional.get());
            saveParkingSpot(parkingEvent);
        }
    }

    public static void saveParkingSpot(ParkingEvent parkingEvent) {
        Long parkingSpotID = parkingEvent.getParkingSpot().getId();
        var parkingSpotOptional = parkingSpotRepository.findById(parkingSpotID);
        if (parkingSpotOptional.isPresent()) {
            ParkingSpot parkingSpot = parkingSpotOptional.get();
            parkingSpot.addParkingEvent(parkingEvent);
            parkingSpotRepository.save(parkingSpot);
        }
    }

    public static void saveCar(ParkingEvent parkingEvent, Car car) {
        car.addParkingEvent(parkingEvent);
        carRepository.save(car);
    }
}
