package com.asep.capstone.abcportal.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_educations")
public class Educations {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long educationId;

    @ManyToOne
    @JoinColumn(name = "user_details_id", referencedColumnName = "userDetailsId")
    private UserAppDetails userAppDetails;

    @Column(name = "university_name", nullable = false)
    private String universityName;

    @Column(name = "majored_in", nullable = false)
    private String majored;

    @Column(name = "start_date", nullable = false)
    private String startDate;

    @Column(name = "end_date", nullable = false)
    private String endDate;


}
