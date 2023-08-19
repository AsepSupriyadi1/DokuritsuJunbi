package com.asep.capstone.abcportal.controllers;


import com.asep.capstone.abcportal.entity.UserAppDetails;
import com.asep.capstone.abcportal.services.UserAppDetailService;
import com.asep.capstone.abcportal.services.UserAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminRestController {

    @Autowired
    UserAppDetailService userAppDetailService;

    @Autowired
    UserAppService userAppService;

    @GetMapping("/detail-user/{userDetailsId}")
    public UserAppDetails userDetails(
            @PathVariable(name = "userDetailsId") Long userDetailsId
    ){

        return userAppDetailService.findUserDetailsById(userDetailsId);
    }

}
