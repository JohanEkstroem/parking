package com.johanekstroem.parking.Controller;

import com.johanekstroem.parking.Entities.Car;
import com.johanekstroem.parking.Entities.ParkingEvent;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



@Controller
public class PagesController {

    @GetMapping("/home")
    public String viewHomePage(){
        return "home";
    }

 @RequestMapping(value = "/parking", method = RequestMethod.POST)
    public String createParking(@ModelAttribute @RequestBody ParkingEvent parkingevent){
       return ResponseEntity.ok().toString();

    }
    @GetMapping ("/parking")
    public String viewParkingPage(Model model){
        model.addAttribute("parkingEvent",new ParkingEvent());
        return "parkingevent";
    }

    // @RequestMapping(value = "/car", method = RequestMethod.POST)
    // public String createParking(@ModelAttribute Car car) {
    //     return ResponseEntity.ok().toString();
    // }

    // @GetMapping("/car")
    // public String viewCarRegisterPage(Model model) {
    //     // I set dummy codes until I can get req body from login page to show username
    //     // in frontend
    //     // var customer = new Customer();
    //     // customer.setId(1L);
    //     // customer.setFirstName("Haeju");
    //     // customer.setLastName("Dummy");

    //     var car = new Car();
    //     car.setId(1L);

    //     model.addAttribute("car", car);
    //     return "carregister";
    // }

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


}
