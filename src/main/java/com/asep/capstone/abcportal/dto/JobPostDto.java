package com.asep.capstone.abcportal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobPostDto {

    private String jobTitle;
    private String jobDesc;
    private String jobType;
    private String companyId;

}
