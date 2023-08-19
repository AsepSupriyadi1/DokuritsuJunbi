package com.asep.capstone.abcportal.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_licenses")
public class Licenses {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long licenseId;

    @ManyToOne
    @JoinColumn(name = "user_details_id", referencedColumnName = "userDetailsId")
    private UserAppDetails userAppDetails;

    @Column(name = "license_name", nullable = false)
    private String licenseName;

    @Column(nullable = false)
    private String organization;

    @Column(name = "date_issued", nullable = false)
    private String dateIssued;

}
