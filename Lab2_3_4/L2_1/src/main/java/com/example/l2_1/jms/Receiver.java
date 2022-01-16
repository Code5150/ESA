package com.example.l2_1.jms;

import com.example.l2_1.entity.Log;
import com.example.l2_1.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Receiver {
    @Autowired
    private NotificationService notificationService;

    @JmsListener(
            destination = JmsHelper.MAILBOX_DESTINATION_NAME,
            containerFactory = JmsHelper.FACTORY_NAME,
            id = "jms_receiver"
    )
    public void receiveMessage(Log log) {
        System.out.println("Info: ====== start sending notifications ======");
        notificationService.sendNotifications(log, JmsHelper.APPLICATION_EMAIL);
        System.out.println("Info: ====== sending notifications completed ======");
    }
}
