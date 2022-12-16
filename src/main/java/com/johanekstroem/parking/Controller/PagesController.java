package com.johanekstroem.parking.Controller;

import com.johanekstroem.parking.Entities.ParkingEvent;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



@Controller
public class PagesController {

    @GetMapping("/")
    public String viewHomePage(){
        return "index";
    }

 @RequestMapping(value = "/parking", method = RequestMethod.POST)
    public String createParking(@ModelAttribute ParkingEvent parkingevent){
       return ResponseEntity.ok().toString();

    }
    @GetMapping ("/parking")
    public String viewParkingPage(Model model){
        model.addAttribute("parkingEvent",new ParkingEvent());
        return "parkingevent";
    }

    @GetMapping("/login")
    public String viewLoginPage(){
        return "login";
    }

    @GetMapping("/saved")
    public String viewParkingStarted(){
        return "saved";
    }

    @GetMapping("/ops")
    public String viewParkingNotStarted(){
        return "ops";
    }

    @GetMapping("/access-denied")
    public String accessDenied() {
        return "/error/access-denied";
    }

    @GetMapping("/createuser")
    public String createNewUserForm() {
        return "/createuser";
    }

}