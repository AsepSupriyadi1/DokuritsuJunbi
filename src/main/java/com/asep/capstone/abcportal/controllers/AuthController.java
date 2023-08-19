package com.asep.capstone.abcportal.controllers;


import com.asep.capstone.abcportal.dto.UserRequest;
import com.asep.capstone.abcportal.entity.UserApp;
import com.asep.capstone.abcportal.entity.UserAppDetails;
import com.asep.capstone.abcportal.services.UserAppDetailService;
import com.asep.capstone.abcportal.services.UserAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
public class AuthController {


    @Autowired
    private UserAppService userAppService;

    @Autowired
    private UserAppDetailService userAppDetailService;

    @GetMapping("/login")
    public String login(){
        return "auth/login";
    }

    @GetMapping("/signup")
    public String signup(Model model){
        model.addAttribute("registrationRequest", new UserRequest());
        return "auth/registration";
    }

    @PostMapping("/signup")
    public String signup( @ModelAttribute("user") UserRequest registrationRequest, Model model){

        UserApp userApp = new UserApp();
        userApp.setEmail(registrationRequest.getEmail());
        userApp.setPassword(registrationRequest.getPassword());

        UserAppDetails userAppDetails = new UserAppDetails();
        userAppDetails.setFirstName(registrationRequest.getFirstname());
        userAppDetails.setLastName(registrationRequest.getLastName());
        userAppDetails.setCountry(registrationRequest.getCountry());
        userAppDetails.setUser(userApp);

        try {

            userAppService.register(userApp);
            userAppDetailService.save(userAppDetails);

            return "auth/thankyou";

        }catch (Exception e){

            System.out.println(e.getMessage());
            return "errors occurred";

        }

    }


}
