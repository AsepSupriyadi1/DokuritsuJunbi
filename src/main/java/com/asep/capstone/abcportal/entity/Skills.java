package com.asep.capstone.abcportal.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_skills")
public class Skills {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long skillId;

    @ManyToOne
    @JoinColumn(name = "user_details_id", referencedColumnName = "userDetailsId")
    private UserAppDetails userAppDetails;


    @Column(name = "skill_name", nullable = false)
    private String skillName;

}
