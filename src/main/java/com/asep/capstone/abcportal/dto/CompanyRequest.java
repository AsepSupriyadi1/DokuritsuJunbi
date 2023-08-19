package com.asep.capstone.abcportal.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyRequest {
    
    private String companyName;
    private String companyField;
    private String companyCountry;
    private String companyAddress;
    private MultipartFile companyLogo;

}
