package com.asep.capstone.abcportal.services;


import com.asep.capstone.abcportal.entity.PasswordResetToken;
import com.asep.capstone.abcportal.repositories.PasswordTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Service
public class PasswordTokenService {


    @Autowired
    private PasswordTokenRepository tokenRepository;


    public void saveToken(PasswordResetToken passwordResetToken){
        passwordResetToken.setExpiredDate(LocalDateTime.now().plusMinutes(2));
        tokenRepository.save(passwordResetToken);
    }


    public PasswordResetToken findByToken(String token){

        PasswordResetToken passwordResetToken = tokenRepository.findByToken(token).orElseThrow(
                () -> new RuntimeException("Token not found")
        );

        Date currentDate = new Date();
        boolean isExpired = passwordResetToken.getExpiredDate().getMinute() < LocalDateTime.now().getMinute();

        if(isExpired){
            throw new RuntimeException("Token already expired");
        }

        return passwordResetToken;
    }

    public PasswordResetToken findById(Long tokenId){

        return tokenRepository.findById(tokenId).orElseThrow(
                () -> new RuntimeException("Token Not Found")
        );

    }

    public void deleteTokenById(Long tokenId){
         tokenRepository.deleteById(tokenId);
    }






}
