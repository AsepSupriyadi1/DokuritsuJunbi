package com.asep.capstone.abcportal.dto;


import com.asep.capstone.abcportal.entity.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {

    private Long userId;
    private String firstname;
    private String lastName;
    private String email;
    private String password;
    private String country;
    private UserRole userRole;

}
