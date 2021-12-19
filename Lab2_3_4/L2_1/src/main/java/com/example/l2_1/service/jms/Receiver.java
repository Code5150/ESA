package com.example.l2_1.service.jms;

import com.example.l2_1.entity.Email;
import com.example.l2_1.entity.Log;
import com.example.l2_1.repository.EmailRepository;
import com.example.l2_1.repository.LogRepository;
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
        email.setToEmail("example@mail.com");
        email.setBody(log.getDetails());

        emailRepository.save(email);

        System.out.println("Info: ====== sending massage completed ======");
    }

}
