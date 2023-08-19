package com.asep.capstone.abcportal.controllers;


import com.asep.capstone.abcportal.entity.*;
import com.asep.capstone.abcportal.repositories.EducationRepository;
import com.asep.capstone.abcportal.repositories.LicenseRepository;
import com.asep.capstone.abcportal.repositories.SkillRepository;
import com.asep.capstone.abcportal.services.ApplicationService;
import com.asep.capstone.abcportal.services.JobAndCompanyService;
import com.asep.capstone.abcportal.services.PostServices;
import com.asep.capstone.abcportal.services.UserAppDetailService;
import com.asep.capstone.abcportal.services.UserAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

@Controller
public class DashboardController {


    @Autowired
    private UserAppService userAppService;

    @Autowired
    private UserAppDetailService userAppDetailService;

    @Autowired
    private EducationRepository educationRepository;

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private LicenseRepository licenseRepository;

    @Autowired
    private PostServices postServices;

    @Autowired
    private JobAndCompanyService jobAndCompanyService;

    @Autowired
    private ApplicationService applicationService;


    @GetMapping("/feed")
    public String dashboard(Model model) throws ParseException {

        List<Post> listPost = postServices.findAllPost();

        model.addAttribute("allThreads", listPost);


        setModel(model);
        return "dashboard/dashboard";
    }


    @GetMapping("/profile")
    public String profile(Model model) {
        setModel(model);
        return "dashboard/index";
    }


    @PostMapping("/update-profile")
    public String updateProfile(@ModelAttribute("userDetails") UserAppDetails userDetails) {
        userAppDetailService.update(userDetails);
        return "redirect:profile";
    }


    @PostMapping("/add-skill")
    public String addSkill(@RequestParam(value = "skillName") String skillName) {

        Skills skills = new Skills();
        UserAppDetails userAppDetails = userAppService.getCurrentUser().getUserAppDetails();
        skills.setSkillName(skillName);
        skills.setUserAppDetails(userAppDetails);

        skillRepository.save(skills);
        return "redirect:profile";

    }


    @PostMapping("/add-education")
    public String addEducation(
            @RequestParam(value = "universityName") String universityName,
            @RequestParam(value = "majored") String majored,
            @RequestParam(value = "startDate") String startDate,
            @RequestParam(value = "endDate") String endDate
    ) {

        Educations education = new Educations();
        UserAppDetails userAppDetails = userAppService.getCurrentUser().getUserAppDetails();
        education.setUniversityName(universityName);
        education.setMajored(majored);
        education.setStartDate(startDate);
        education.setEndDate(endDate);
        education.setUserAppDetails(userAppDetails);

        educationRepository.save(education);

        return "redirect:profile";

    }


    @PostMapping("/add-license")
    public String addLicense(
            @RequestParam(value = "licenseName") String licenseName,
            @RequestParam(value = "organization") String organization,
            @RequestParam(value = "dateIssued") String dateIssued
    ) {

        Licenses license = new Licenses();
        UserAppDetails userAppDetails = userAppService.getCurrentUser().getUserAppDetails();

        license.setLicenseName(licenseName);
        license.setUserAppDetails(userAppDetails);
        license.setOrganization(organization);
        license.setDateIssued(dateIssued);

        licenseRepository.save(license);

        return "redirect:profile";

    }


    public void setModel(Model model) {
        UserApp currentLoggedUser = userAppService.getCurrentUser();
        UserAppDetails userDetails = currentLoggedUser.getUserAppDetails();

        // -=-=-=-=-= User Details -=-=-=-=-=
        model.addAttribute("loggedUser", currentLoggedUser);
        System.out.println(currentLoggedUser.getUserId());
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


    // ADD THREAD
    @PostMapping("/thread")
    public String saveThread(
            @RequestParam(value = "description") String description,
            @RequestParam(value = "image") MultipartFile image,
            Model model

    ) throws IOException {

        Post post = new Post();
        post.setDescription(description);
        post.setPostImageData(image.getBytes());

        UserApp userApp = userAppService.getCurrentUser();

        postServices.savePost(post, userApp);

        return "redirect:/feed";
    }


//    // LIKE THREAD
//    @GetMapping("/thread/like/{postId}")
//    public String likePost(@RequestParam(value = "postId") Long postId) throws IOException {
//
//        try {
//            postServices.likePost(postId);
//        } catch (Exception e){
//            System.out.println(e.getMessage());
//        }
//
//        return "redirect:/feed";
//    }


    // -=-=-=-=-=-=-=-=-=-= SEARCH FEATURE -=-=-=-=-=-=-=-=


    @GetMapping("/search")  // SEARCH ANYTHING BY KEYWORD
    public String search(@RequestParam(name = "keyword", required = false) String keyword, Model model) {

        setModel(model);
        List<UserAppDetails> lAppDetails = userAppDetailService.findByKeyword(keyword);
        List<JobPost> jPosts = jobAndCompanyService.findJobByKeyword(keyword);
        model.addAttribute("peopleResult", lAppDetails);
        model.addAttribute("jobsResult", jPosts);
        return "search/search";

    }


    @GetMapping("/user")  // SEARCH ANYTHING BY KEYWORD
    public String userDetails(
            @RequestParam(name = "firstName", required = true) String firstName,
            @RequestParam(name = "lastName", required = true) String lastName,
            Model model) {

        try {

            UserAppDetails user = userAppDetailService.findUserByName(firstName, lastName);
            model.addAttribute("result", user);
            setModel(model);
            return "search/result";

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "redirect:/feed";
        }

    }


    @GetMapping("/job")  // SEARCH ANYTHING BY KEYWORD
    public String jobDetails(@RequestParam(name = "jobId", required = true) String jobId, Model model) {

        try {
            JobPost jobPost = jobAndCompanyService.findJobById(Long.parseLong(jobId));
            List<JobPost> lJobPosts = jobAndCompanyService.findJobByKeyword(jobPost.getJobTitle());

            model.addAttribute("result", jobPost);
            model.addAttribute("relevantJobPosts", lJobPosts);

            setModel(model);
            return "search/jobDetails";

        } catch (Exception e) {
            return "redirect:/feed";
        }

    }


    // -=-=-=-=-=-=-=-=-=-= END OF SEARCH FEATURE -=-=-=-=-


    // -=-=-=-=-=-=-=-=-=-=--=-=-=-=-== JOBS FEED FEATURE -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
    @PostMapping("/apply")  // SEARCH ANYTHING BY KEYWORD
    public String applyForAJob(
            @RequestParam(name = "portfolio", required = true) String porto,
            @RequestParam(name = "jobId", required = true) String jobId,
            Model model) {

        try {

            UserApp userApp = userAppService.getCurrentUser();
            JobPost jobPost = jobAndCompanyService.findJobById(Long.parseLong(jobId));
            List<JobPost> lJobPosts = jobAndCompanyService.findJobByKeyword(jobPost.getJobTitle());
            model.addAttribute("result", jobPost);
            model.addAttribute("relevantJobPosts", lJobPosts);
            setModel(model);
            applicationService.saveApplication(porto, jobPost, userApp);
            return "search/jobDetails";

        } catch (Exception e) {
            return "redirect:/feed";
        }
    }


    @GetMapping("/jobs-feed")  // SEARCH ANYTHING BY KEYWORD
    public String applyForAJob(

            Model model) {

        List<JobPost> jobPostList = jobAndCompanyService.getAllJobPosted();
        model.addAttribute("allJobs", jobPostList);
        model.addAttribute("searchedResult", null);
        setModel(model);
        return "dashboard/jobFeed";

    }

    @GetMapping("/search-jobs-feed")  // SEARCH ANYTHING BY KEYWORD
    public String applyForAJob(
            @RequestParam(name = "jobTitle", required = false) String jobTitle,
            @RequestParam(name = "jobType", required = false) String jobType,
            @RequestParam(name = "jobCountry", required = false) String jobCountry,
            Model model) {

        List<JobPost> jobPostList = jobAndCompanyService.findJobByTitleCountryType(jobTitle, jobCountry, jobType);
        model.addAttribute("searchedResult", jobPostList);
        model.addAttribute("allJobs", null);

        setModel(model);
        return "dashboard/jobFeed";

    }
    // -=-=-=-=-=-=-=-=-=-=--=-=-=-=-== END OF JOBS FEED FEATURE -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=


}
