package com.asep.capstone.abcportal.services;

import com.asep.capstone.abcportal.entity.UserApp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class EmailAppService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private UserAppService userAppService;


    public boolean sendEmail( String subject, String body, String to){

        List<UserApp> userAppList = userAppService.getALlUser();

        try {

            if(to == null) {
                for(UserApp user : userAppList){

                    SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
                    simpleMailMessage.setFrom("asepsupyad789@gmail.com");
                    simpleMailMessage.setSubject(subject);
                    simpleMailMessage.setTo(user.getEmail());
                    simpleMailMessage.setText(body);

                    javaMailSender.send(simpleMailMessage);

                }
            } else {
                SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
                simpleMailMessage.setFrom("asepsupyad789@gmail.com");
                simpleMailMessage.setSubject(subject);
                simpleMailMessage.setTo(to);
                simpleMailMessage.setText(body);


                javaMailSender.send(simpleMailMessage);
            }


            return true;

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


        return false;


    }


}
