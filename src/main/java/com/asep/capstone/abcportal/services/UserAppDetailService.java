package com.asep.capstone.abcportal.services;


import com.asep.capstone.abcportal.entity.UserApp;
import com.asep.capstone.abcportal.entity.UserAppDetails;
import com.asep.capstone.abcportal.repositories.UserAppDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.util.InputMismatchException;
import java.util.List;

@Service
@Transactional
public class UserAppDetailService {

    @Autowired
    private UserAppDetailRepository userAppDetailRepository;

    @Autowired
    private UserAppService userAppService;

    public void save(UserAppDetails userAppDetails){
        userAppDetailRepository.save(userAppDetails);
    }


    public void update(UserAppDetails userAppDetails){

        UserApp currentUser = userAppService.getCurrentUser();
        userAppDetails.setUser(currentUser);
        userAppDetails.setUserDetailsId(currentUser.getUserAppDetails().getUserDetailsId());
        userAppDetails.setFirstName(userAppDetails.getFirstName());
        userAppDetails.setLastName(userAppDetails.getLastName());
        userAppDetails.setCountry(userAppDetails.getCountry());
        userAppDetails.setAbout(userAppDetails.getAbout());
        userAppDetails.setHeadline(userAppDetails.getHeadline());

        userAppDetailRepository.save(userAppDetails);

    }


    public List<UserAppDetails> getAllUserDetails(){
        return userAppDetailRepository.findAll();
    }

    public List<UserAppDetails> findAllUserByDateRange(String startDate, String endDate){
        return userAppDetailRepository.findUserDetailsByDateRange(startDate, endDate);
    }


    public UserAppDetails findUserDetailsById(Long userDetailsId){
        return userAppDetailRepository.findById(userDetailsId).get();
    }


    public List<UserAppDetails> findByKeyword(String keyword){
        return userAppDetailRepository.findUserDetailsByKeyword(keyword);
    }


    public UserAppDetails findUserByName(String firstName, String lastName){
        return userAppDetailRepository.findByFirstAndLastName(firstName, lastName).orElseThrow(
            () -> new InputMismatchException("User Not Found")
        );
    }


}
