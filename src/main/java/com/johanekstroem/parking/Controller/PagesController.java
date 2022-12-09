package com.johanekstroem.parking.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class PagesController {

    @GetMapping("/")
    public String viewHomePage(Model model){
        return "index";
    }

    @GetMapping("/login")
    public String viewLoginPage(Model model){
        return "login";
    }

    @GetMapping("/access-denied")
    public String accessDenied() {
        return "/error/access-denied";
    }

}
