package com.johanekstroem.parking.Service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

@Service
public class ValidateStopTime {
      public boolean isInFuture(LocalDateTime stoptime){
        return stoptime.isAfter(LocalDateTime.now());    }

   }
