package com.asep.capstone.abcportal.services;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asep.capstone.abcportal.repositories.CompanyRepository;
import com.asep.capstone.abcportal.dto.CompanyRequest;
import com.asep.capstone.abcportal.entity.Company;


@Service
public class CompanyService {
    

    @Autowired
    private CompanyRepository companyRepository;


    public List<Company> getAllCompany(){
        return companyRepository.findAll();
    }

    public void saveCompany(CompanyRequest companyRequest) throws IOException {
       
        Company company = new Company();
        company.setCompanyAddress(companyRequest.getCompanyAddress());
        company.setCompanyField(companyRequest.getCompanyField());
        company.setCompanyName(companyRequest.getCompanyName());
        company.setCountry(companyRequest.getCompanyCountry());
        company.setCompanyLogo(companyRequest.getCompanyLogo().getBytes());

        companyRepository.save(company);        

    }



}
