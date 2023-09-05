package com.asep.capstone.abcportal.services;


import com.asep.capstone.abcportal.dto.UserRequest;
import com.asep.capstone.abcportal.entity.UserApp;
import com.asep.capstone.abcportal.entity.UserAppDetails;
import com.asep.capstone.abcportal.entity.UserRole;
import com.asep.capstone.abcportal.repositories.UserAppDetailRepository;
import com.asep.capstone.abcportal.repositories.UserAppRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.List;

@Service
@Transactional
public class UserAppService implements UserDetailsService {


    private final UserAppRepository userAppRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    private final UserAppDetailRepository userAppDetailRepository;

    @Autowired
    public UserAppService(UserAppRepository userAppRepository, BCryptPasswordEncoder passwordEncoder, UserAppDetailRepository userAppDetailRepository) {
        this.userAppRepository = userAppRepository;
        this.passwordEncoder = passwordEncoder;
        this.userAppDetailRepository = userAppDetailRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserApp userApp = userAppRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User not found with username '%s%'" +  email)));

        return new User(userApp.getUsername(), userApp.getPassword(), userApp.getAuthorities());
    }

    public UserApp register(UserApp user){

        boolean isUserExist = userAppRepository.findByEmail(user.getEmail()).isPresent();

        if(isUserExist){
            throw new RuntimeException(String.format("user with email '%s' already exist", user.getEmail()));
        }

        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setActive(false);
        user.setRole(UserRole.USER);
        user.setCreatedAt(LocalDate.now().toString());

        return userAppRepository.save(user);
    }



    public UserApp getCurrentUser() throws RuntimeException{

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = null;
        if(principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
           return null;
        }

        return userAppRepository.findByEmail(username).orElseThrow(() -> new RuntimeException("Not Found"));

    }


    public List<UserApp> getALlUser(){
        return userAppRepository.findAll();
    }

    public UserApp getALlUser(Long userAppId){
        return userAppRepository.findById(userAppId).orElseThrow(() -> new RuntimeException("userAppId not found"));
    }


    public void deleteUser(Long userAppId){
        userAppRepository.deleteById(userAppId);
    }


    public void updateUser(UserRequest userRequest){

        UserApp userApp = userAppRepository.findById(userRequest.getUserId()).get();
        UserAppDetails userAppDetails = userAppDetailRepository.findByUser(userApp);

        userApp.setRole(userRequest.getUserRole());
        userApp.setEmail(userRequest.getEmail());
        userAppRepository.save(userApp);

        userAppDetails.setFirstName(userRequest.getFirstname());
        userAppDetails.setLastName(userRequest.getLastName());
        userAppDetails.setCountry(userRequest.getCountry());
        userAppDetailRepository.save(userAppDetails);

    }



    public UserApp findUserById(Long userAppId){
        return userAppRepository.findById(userAppId).get();
    }

    public UserApp findByEmail(String email){
        return userAppRepository.findByEmail(email).get();
    }


    public void updatePassword(UserApp userApp){
        userAppRepository.save(userApp);
    }



}
