package com.asep.capstone.abcportal.repositories;


import com.asep.capstone.abcportal.entity.Skills;
import com.asep.capstone.abcportal.entity.UserAppDetails;
import org.springframework.data.jpa.repository.JpaRepository;



import java.util.List;


public interface SkillRepository extends JpaRepository<Skills, Long>  {

    List<Skills> findByUserAppDetails(UserAppDetails userAppDetails);

}
