package com.asep.capstone.abcportal.controllers;


import com.asep.capstone.abcportal.dto.ResetPasswordDto;
import com.asep.capstone.abcportal.dto.UserRequest;
import com.asep.capstone.abcportal.entity.PasswordResetToken;
import com.asep.capstone.abcportal.entity.UserApp;
import com.asep.capstone.abcportal.entity.UserAppDetails;
import com.asep.capstone.abcportal.services.EmailAppService;
import com.asep.capstone.abcportal.services.PasswordTokenService;
import com.asep.capstone.abcportal.services.UserAppDetailService;
import com.asep.capstone.abcportal.services.UserAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.Date;
import java.util.UUID;


@Controller
public class AuthController {


    @Autowired
    private UserAppService userAppService;

    @Autowired
    private PasswordTokenService passwordTokenService;

    @Autowired
    private UserAppDetailService userAppDetailService;

    @Autowired
    private EmailAppService emailAppService;


    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


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
    public String signup( @ModelAttribute("user") UserRequest registrationRequest, Model model, RedirectAttributes rd){

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
        }catch (RuntimeException e){

            rd.addFlashAttribute("RegisterError", e.getMessage());
            System.out.println(e.getMessage());
            return "redirect:/signup";

        } catch (Exception e){
            return "redirect:/signup";
        }

    }


    // FORGOT PASSWORD PAGE
    @GetMapping("forgot-password")
    public String forgotPasswordPage(){
        return "forget/forgotpassword";
    }


    @PostMapping("forgot-password/submit")
    public String forgotPasswordPage(
            @RequestParam(required = true) String email,
            Model model,
            HttpServletRequest request,
            RedirectAttributes rd
    ){

        UserApp userApp;

        try {
            userApp = userAppService.findByEmail(email);
        } catch (Exception e){
            rd.addFlashAttribute("error", "Email Not Found");
            return "redirect:/forgot-password";
        }


        // SET THE ENTITY
        PasswordResetToken passwordResetToken = new PasswordResetToken();
        passwordResetToken.setUserId(userApp.getUserId());
        passwordResetToken.setToken(UUID.randomUUID().toString());

        // SAVE THE ENTITY
        passwordTokenService.saveToken(passwordResetToken);


        // CREATE THE URL
        String serverUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
        String fullUrl = serverUrl + "/reset-password?token=" + passwordResetToken.getToken();

        // SEND THE EMAIL
        emailAppService.sendEmail("Reset Password", "This is the Link : " + fullUrl, userApp.getEmail());


        rd.addFlashAttribute("successResetPage", "We have send you an email");
        return "redirect:/forgot-password";
    }


    @GetMapping("/reset-password")
    public String resetPasswordPage(@RequestParam(name = "token") String token, Model model, RedirectAttributes rd){

        try {
            PasswordResetToken passwordResetToken = passwordTokenService.findByToken(token);
            model.addAttribute("userId", passwordResetToken.getUserId());
            model.addAttribute("tokenId", passwordResetToken.getPasswordResetTokenId());

        } catch (Exception e){
            rd.addFlashAttribute("tokenError", e.getMessage());
            return "redirect:/forgot-password";
        }


        return "forget/resetpassword";


    }


    @PostMapping("/reset-password")
    public String resetPasswordSubmit(
            @RequestParam(name = "userId") String userId,
            @RequestParam(name = "tokenId") String tokenId,
            @RequestParam(name = "password") String newPassword,
            Model model, RedirectAttributes rd){

        UserApp userApp = userAppService.findUserById(Long.parseLong(userId));

        // SET THE NEW PASSWORD
        String encodedPassword = passwordEncoder.encode(newPassword);
        userApp.setPassword(encodedPassword);
        userAppService.updatePassword(userApp);

        //DELETE TOKEN
        passwordTokenService.deleteTokenById(Long.parseLong(tokenId));

        rd.addFlashAttribute("passwordSuccess", "Password reset successfully");
        return "redirect:/login";
    }





}
