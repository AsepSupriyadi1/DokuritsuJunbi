package com.asep.capstone.abcportal.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asep.capstone.abcportal.entity.Application;
import com.asep.capstone.abcportal.entity.JobPost;
import com.asep.capstone.abcportal.entity.UserApp;
import com.asep.capstone.abcportal.repositories.ApplicationAppRepository;

@Service
public class ApplicationService {
    
    @Autowired
    private ApplicationAppRepository appRepository;

    @Autowired
    private JobAndCompanyService jobAndCompanyService;

    @Autowired
    private UserAppService userAppService;



    public Application saveApplication(String porto, JobPost jobPost, UserApp currentUser){


        Application application = new Application();
        application.setDatetime(new Date());
        application.setJobPost(jobPost);
        application.setUserAppDetails(currentUser.getUserAppDetails());
        application.setWebPortfolioUrl(porto);

        return appRepository.save(application);

    }


    public List<Application> findAll(){
        return appRepository.findAll();
    }


}
