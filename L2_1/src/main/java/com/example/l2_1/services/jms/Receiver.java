package com.example.l2_1.services.jms;

import com.example.l2_1.entities.Email;
import com.example.l2_1.entities.Log;
import com.example.l2_1.repositories.EmailRepository;
import com.example.l2_1.repositories.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;


@Component
public class Receiver {

    @Autowired
    private LogRepository logRepository;

    @Autowired
    private EmailRepository emailRepository;


    @JmsListener(destination = JmsHelper.DESTINATION_NAME, containerFactory = JmsHelper.FACTORY_NAME)
    public void receiveMessage(Log log) {
       logRepository.save(log);

        System.out.println("Info: ====== start sending massage ======");

        Email email = new Email();
        email.setTo("example@mail.com");
        email.setBody(log.getDetails());

        emailRepository.save(email);

        System.out.println("Info: ====== sending massage completed ======");
    }

}
