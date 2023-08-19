package com.asep.capstone.abcportal.repositories;

import com.asep.capstone.abcportal.entity.Educations;
import com.asep.capstone.abcportal.entity.UserAppDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface EducationRepository extends JpaRepository<Educations, Long> {

    List<Educations> findByUserAppDetails(UserAppDetails userAppDetails);

}
