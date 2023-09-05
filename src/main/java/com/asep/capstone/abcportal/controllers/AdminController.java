package com.asep.capstone.abcportal.controllers;


import com.asep.capstone.abcportal.dto.CompanyRequest;
import com.asep.capstone.abcportal.dto.JobPostDto;
import com.asep.capstone.abcportal.dto.UserRequest;
import com.asep.capstone.abcportal.entity.*;
import com.asep.capstone.abcportal.repositories.EducationRepository;
import com.asep.capstone.abcportal.repositories.LicenseRepository;
import com.asep.capstone.abcportal.repositories.SkillRepository;
import com.asep.capstone.abcportal.services.ApplicationService;
import com.asep.capstone.abcportal.services.CompanyService;
import com.asep.capstone.abcportal.services.EmailAppService;
import com.asep.capstone.abcportal.services.JobAndCompanyService;
import com.asep.capstone.abcportal.services.UserAppDetailService;
import com.asep.capstone.abcportal.services.UserAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class AdminController {
    @Autowired
    private UserAppService userAppService;

    @Autowired
    private UserAppDetailService userAppDetailService;

    @Autowired
    private EmailAppService emailAppService;

    @Autowired
    private EducationRepository educationRepository;

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private LicenseRepository licenseRepository;


    @Autowired
    private JobAndCompanyService jobAndCompanyService;


    @Autowired
    private CompanyService companyService;


    @Autowired
    private ApplicationService applicationService;


    // -=-=-=-=-=-=-=-= DASHBOARD ADMIN CONTROLLER -=-=-=-=-=-=-=-=
    @GetMapping("/dashboard")
    public String index(){

        UserApp userApp = userAppService.getCurrentUser();

        if(userApp.getRole().name().equals("USER")){
            throw new AccessDeniedException("");
        }

        return "administrator/index";
    }





    // -=-=-=-=-=-=-=-= USER ADMIN CONTROLLER -=-=-=-=-=-=-=-=

    @GetMapping("/all-users")
    public String allUsers(Model model){
        List<UserApp> userList = userAppService.getALlUser();
        model.addAttribute("users", userList);
        return "administrator/view-users";
    }


    @GetMapping("/edit-user/{userId}")
    public String userDetail(@PathVariable("userId") Long userId, Model model) {
        setModel(model, userId);
        return "administrator/editUsers";
    }


    @GetMapping("/edit-user")
    public String userDetail() {
        return "administrator/editUsers";
    }

    @GetMapping("/post-job")
    public String postJob() {
        return "administrator/postJob";
    }


    @PostMapping("/delete-user/{userAppId}")
    public String generateReport(
            @PathVariable(name = "userAppId") Long userAppId
    ){
        UserRequest userRequest = new UserRequest();
        userRequest.setUserId(userAppId);
        userAppService.updateUser(userRequest);
        return "administrator/report";
    }







    // -=-=-=-=-=-=-=-= BULK EMAIL CONTROLLER -=-=-=-=-=-=-=-=

    @GetMapping("/bulk-email")
    public String email(){
        return "administrator/bulkemail";
    }

    @PostMapping("/send-bulk-email")
    public String sendBulkEmail(
            @RequestParam(value = "subject") String subject,
            @RequestParam(value = "body") String  body,
            RedirectAttributes rd
    ){

        boolean isSuccess = emailAppService.sendEmail(subject, body, null);
        if(isSuccess){
            rd.addFlashAttribute("success", "true");
        }

        rd.addFlashAttribute("success", "false");
        return "administrator/bulkemail";
    }

    // -=-=-=-=-=-=-=-= END OF BULK EMAIL CONTROLLER -=-=-=-=-=-=-=-=-=




    // -=-=-=-=-=-=-=-=-= MANAGE COMPANY -=-=-=-=-=-=-=-=-=-=
    @GetMapping("/all-company")
    public String fetchALlCompanies(Model model){
        List<Company> companies = companyService.getAllCompany();
        model.addAttribute("companies", companies);
        return "administrator/view_companies";
    }

    @GetMapping("/add-company")
    public String addCompanyPage(Model model){
        model.addAttribute("companyRequestDto", new CompanyRequest());
        return "administrator/addCompany";
    }


    @PostMapping("/saveJob")
    public String saveCompany(@ModelAttribute CompanyRequest companyRequest, Model model){
      
        try {
            companyService.saveCompany(companyRequest);
        } catch (Exception e){
            System.out.println(e.getMessage());
            return "administrator/addCompany";
        }

        return "redirect:/all-company";
    }




    // -=-=-=-=-=-=-=-=-= END OF MANAGE COMPANY -=-=-=-=-=-=-=-=-=-=


    // -=-=-=-=-=-=-=-=-= MANAGE JOB CONTROLLER -=-=-=-=-=-=-=-=-=

    @GetMapping("/all-job")
    public String fetchALlJobs(Model model){

        List<JobPost> jobPostList = jobAndCompanyService.getAllJobPosted();


        if(jobPostList.size() > 0){
            model.addAttribute("allJobPosted", jobPostList);
        } else {
            model.addAttribute("allJobPosted", "empty");
        }


        return "administrator/alljob";
    }

    @GetMapping("/add-job")
    public String addJobPage(Model model){

        List<Company> lCompanies = companyService.getAllCompany();
        model.addAttribute("companies", lCompanies);
        model.addAttribute("jobPostDto", new JobPostDto());
        return "administrator/postJob";
    }


   @PostMapping("/save-job")
   public String saveJobs(@ModelAttribute JobPostDto jobPostDto, Model model){
       jobAndCompanyService.postJob(jobPostDto);
       return "redirect:/all-job";
   }

    // -=-=-=-=-=-=-=-=-= END OF MANAGE JOB CONTROLLER -=-=-=-=-=-=-=-=-=





    // -=-=-=-=-=-=-=-=-= MANAGE JOB CONTROLLER -=-=-=-=-=-=-=-=-=
    @GetMapping("/all-applications")
    public String fetchAllApplications(Model model){

        List<Application> applications = applicationService.findAll();
        model.addAttribute("allApplications", applications);

        return "administrator/view_applications";
    }



   // -=-=-=-=-=-=-=-=-= END OF MANAGE APPLICATION CONTROLLER -=-=-=-=-=-=-=-=-=


    // -=-=-=-=-=-=-=-= REPORT CONTROLLER -=-=-=-=-=-=-=-=

    @GetMapping("/report")
    public String report(){
        return "administrator/generate-report";
    }

    @PostMapping("/generate-report")
    public String generateReport(
            @RequestParam(value = "startDate") String startDate,
            @RequestParam(value = "endDate") String  endDate,
            RedirectAttributes rd,
            Model model
    ){


        List<UserAppDetails> list =  userAppDetailService.findAllUserByDateRange(startDate, endDate);
        model.addAttribute("Users", list);
        rd.addFlashAttribute("success", "true");
        rd.addFlashAttribute("total", list.size());

        return "administrator/report";
    }






    public void setModel(Model model, Long userId){
        UserApp currentLoggedUser = userAppService.findUserById(userId);
        UserAppDetails userDetails = currentLoggedUser.getUserAppDetails();

        // -=-=-=-=-= User Details -=-=-=-=-=
        model.addAttribute("loggedUser", currentLoggedUser);
        model.addAttribute("userDetails", userDetails);
        model.addAttribute("email", currentLoggedUser.getEmail());
        model.addAttribute("f", userDetails.getFirstName().charAt(0));
        model.addAttribute("l", userDetails.getLastName().charAt(0));
        model.addAttribute("firstName", userDetails.getFirstName());
        model.addAttribute("lastName", userDetails.getLastName());
        model.addAttribute("country", userDetails.getCountry());
        model.addAttribute("headline", userDetails.getHeadline());
        model.addAttribute("about", userDetails.getAbout());

        // -=-=-=-=-= Section -=-=-=-=-=
        List<Educations> loggedUserEducations = educationRepository.findByUserAppDetails(userDetails);
        List<Skills> loggedUserSkills = skillRepository.findByUserAppDetails(userDetails);
        List<Licenses> loggedUserLicenses = licenseRepository.findByUserAppDetails(userDetails);

        model.addAttribute("educations", loggedUserEducations);
        model.addAttribute("skills", loggedUserSkills);
        model.addAttribute("licenses", loggedUserLicenses);

    }





}
