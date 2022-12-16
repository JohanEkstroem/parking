package com.johanekstroem.parking.Controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.client.RestTemplate;

import com.johanekstroem.parking.Models.UserCredentials;

@Controller
public class PagesController {
    @Autowired
    RestTemplate restTemplate;
    @GetMapping("/")
    public String viewHomePage(Model model){
        return "index";
    }

    @GetMapping("/login")
    public String viewLoginPage(Model model) {
        return "login";
    }

    @GetMapping("/createuser")
    public String viewCreateUserPage(Model model) {
        model.addAttribute("UserCredentials", new UserCredentials());
        return "createuser";
    }

    @GetMapping("/access-denied")
    public String accessDenied() {
        return "/error/access-denied";
    }

 

}
