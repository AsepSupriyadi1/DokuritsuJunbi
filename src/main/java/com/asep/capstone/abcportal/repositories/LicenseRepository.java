package com.asep.capstone.abcportal.repositories;


import com.asep.capstone.abcportal.entity.Licenses;
import com.asep.capstone.abcportal.entity.UserAppDetails;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;


public interface LicenseRepository extends JpaRepository<Licenses, Long> {

    List<Licenses> findByUserAppDetails(UserAppDetails userAppDetails);

}
