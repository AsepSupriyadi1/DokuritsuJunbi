package com.asep.capstone.abcportal.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_userdetails")
public class UserAppDetails {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userDetailsId;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String country;

    private String headline;
    private String about;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "userId")
    private UserApp user;

    @JsonIgnore
    @OneToMany(mappedBy = "userAppDetails", cascade = CascadeType.ALL)
    private List<Educations> educations = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "userAppDetails", cascade = CascadeType.ALL)
    private List<Licenses> licenses = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "userAppDetails", cascade = CascadeType.ALL)
    private List<Skills> skills = new ArrayList<>();

}
