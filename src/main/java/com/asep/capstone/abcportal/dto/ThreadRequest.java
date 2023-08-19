package com.asep.capstone.abcportal.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ThreadRequest {

    private String description;
    private MultipartFile postImageData;

}
