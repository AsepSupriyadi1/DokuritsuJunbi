package com.asep.capstone.abcportal.services;


import com.asep.capstone.abcportal.dto.JobPostDto;
import com.asep.capstone.abcportal.entity.Company;
import com.asep.capstone.abcportal.entity.JobPost;
import com.asep.capstone.abcportal.entity.constraint.JobType;
import com.asep.capstone.abcportal.repositories.CompanyRepository;
import com.asep.capstone.abcportal.repositories.JobPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;

@Service
@Transactional
public class JobAndCompanyService {


    @Autowired
    private JobPostRepository jobPostRepository;

    @Autowired
    private CompanyRepository companyRepository;




    public List<JobPost> getAllJobPosted(){
        return jobPostRepository.findAll();
    }

    public JobPost postJob(JobPostDto jobPostDto){

        JobPost jobPost = new JobPost();
        Company company =companyRepository.findById(Long.parseLong(jobPostDto.getCompanyId())).orElseThrow(
            () -> new RuntimeException("Company Not Found")
        );

        jobPost.setJobTitle(jobPostDto.getJobTitle());
        jobPost.setJobDesc(jobPostDto.getJobDesc());
        jobPost.setDatetime(new Date());


        try {
            jobPost.setJobType(JobType.valueOf(jobPostDto.getJobType()));
        } catch(Exception e){
            throw new InputMismatchException("Job Type Not Found");
        }
      

        jobPost.setCompany(company);

        return jobPostRepository.save(jobPost);
    }


    
    public List<JobPost> findJobByKeyword(String keyword){
        return jobPostRepository.findJobByKeyword(keyword);
    }


    public JobPost findJobByTitle(String jobTitle){
        return jobPostRepository.findJobByTitle(jobTitle).orElseThrow(
            () -> new InputMismatchException("Job Not Found")
        );
    }


    public JobPost findJobById(Long jobId){
        return jobPostRepository.findById(jobId).orElseThrow(
                () -> new InputMismatchException("Job Not Found")
        );
    }




    public List<JobPost> findJobByTitleCountryType(String jobTitle, String country, String type){

       List<JobPost> listResult = new ArrayList<>();

       List<JobPost> listKeywordResult = jobPostRepository.findJobByKeyword(jobTitle);
       List<JobPost> listCountryResult = jobPostRepository.findJobByCountry(country);
       List<JobPost> listTypeResult = jobPostRepository.findJobByType(type);

       if(listKeywordResult.size() != 0){
           listResult.addAll(listKeywordResult);
       }

        if(listCountryResult.size() != 0){
            listResult.addAll(listCountryResult);
        }

        if(listTypeResult.size() != 0){
            listResult.addAll(listTypeResult);
        }

       return  listResult;

    }





}
