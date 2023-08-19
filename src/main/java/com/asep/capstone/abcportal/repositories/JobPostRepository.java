package com.asep.capstone.abcportal.repositories;

import com.asep.capstone.abcportal.entity.JobPost;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface JobPostRepository extends JpaRepository<JobPost, Long> {

    @Query("SELECT j FROM JobPost j WHERE j.jobTitle LIKE %:keyword%")
    List<JobPost> findJobByKeyword(@Param("keyword") String keyword);

    @Query("SELECT j FROM JobPost j WHERE j.jobType = :jobType")
    List<JobPost> findJobByType(@Param("jobType") String jobType);

    @Query("SELECT j FROM JobPost j WHERE j.company.country = :country")
    List<JobPost> findJobByCountry(@Param("country") String country);


    @Query("SELECT j FROM JobPost j WHERE j.jobTitle = :jobTitle")
    Optional<JobPost> findJobByTitle(@Param("jobTitle") String jobTitle);

}
