package com.asep.capstone.abcportal.repositories;

import com.asep.capstone.abcportal.entity.UserApp;
import com.asep.capstone.abcportal.entity.UserAppDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.query.Param;


import java.util.List;
import java.util.Optional;



public interface UserAppDetailRepository extends JpaRepository<UserAppDetails, Long> {

    @Query("SELECT n FROM UserAppDetails n WHERE n.user.createdAt BETWEEN :startDate AND :endDate")
    List<UserAppDetails> findUserDetailsByDateRange(String startDate, String endDate);

    @Query("SELECT n FROM UserAppDetails n WHERE n.firstName LIKE %:keyword% OR n.lastName LIKE %:keyword% OR n.headline LIKE %:keyword%")
    List<UserAppDetails> findUserDetailsByKeyword(@Param("keyword") String keyword);

    @Query("SELECT u FROM UserAppDetails u WHERE u.firstName = :firstName AND u.lastName = :lastName")
    Optional<UserAppDetails> findByFirstAndLastName(@Param("firstName") String firstName, @Param("lastName") String lastName);

    UserAppDetails findByUser(UserApp user);

}
