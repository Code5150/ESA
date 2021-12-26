package com.example.l2_1.jms;

import com.example.l2_1.entity.Email;
import com.example.l2_1.entity.Log;
import com.example.l2_1.repository.LogRepository;
import com.example.l2_1.repository.NotificationRepository;
import com.example.l2_1.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Receiver {

    @Autowired
    private LogRepository logRepository;

    @Autowired
    private NotificationService notificationService;


    @JmsListener(destination = JmsHelper.DESTINATION_NAME, containerFactory = JmsHelper.FACTORY_NAME)
    public void receiveMessage(Log log) {
        logRepository.save(log);

        System.out.println("Info: ====== start sending notifications ======");
        notificationService.sendNotifications(log, JmsHelper.APPLICATION_EMAIL);
        System.out.println("Info: ====== sending notifications completed ======");
    }

}
