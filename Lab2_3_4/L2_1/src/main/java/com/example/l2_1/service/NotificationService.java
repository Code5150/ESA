package com.example.l2_1.service;

import com.example.l2_1.entity.Email;
import com.example.l2_1.entity.Log;
import com.example.l2_1.entity.Notification;
import com.example.l2_1.entity.SubscriptionType;
import com.example.l2_1.repository.NotificationRepository;
import com.example.l2_1.repository.SubscriptionTypeRepository;
import com.example.l2_1.util.DBChanges;
import com.example.l2_1.util.SubscriptionTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private SubscriptionTypeService subscriptionTypeService;

    public void sendNotifications(Log log, String fromEmail){

        List<Email> emailList = getEmailsBySubscription(log);
        String theme = generateTheme(log);
        String description = generateDescription(log);
        for(Email email: emailList) {
            Notification notification = new Notification();
            notification.setFromEmail(fromEmail);
            notification.setToEmail(email.getName());
            notification.setTheme(theme);
            notification.setDescription(description);
            notificationRepository.save(notification);
        }

    }

    private String generateTheme(Log log) {
        return log.getKindChange() + " " + log.getEntity();
    }

    private String generateDescription(Log log) {
        return "Description: user " + log.getKindChange() + " "
                + log.getEntity() + "\nAnswer was: " + log.getDetails();
    }

    private List<Email> getEmailsBySubscription(Log log) {
        List<Email> resultEmailsList = new ArrayList<>();
        if(log.getKindChange().equals(DBChanges.READ.toString())) {
            SubscriptionType subscriptionType = subscriptionTypeService.findByName(SubscriptionTypeEnum.ALL.toString());

            resultEmailsList.addAll(
                    emailService.findBySubscriptionType(subscriptionType)
            );
        }
        else if(
                log.getKindChange().equals(DBChanges.UPDATE.toString())
                || log.getKindChange().equals(DBChanges.CREATE.toString())
        ){
            SubscriptionType subscriptionTypeALL = subscriptionTypeService.findByName(SubscriptionTypeEnum.ALL.toString());
            SubscriptionType subscriptionTypeEDIT = subscriptionTypeService.findByName(SubscriptionTypeEnum.EDIT.toString());

            resultEmailsList.addAll(
                    emailService.findBySubscriptionType(subscriptionTypeALL)
            );
            resultEmailsList.addAll(
                   emailService.findBySubscriptionType(subscriptionTypeEDIT)
            );
        }
        else if(
                log.getKindChange().equals(DBChanges.DELETE.toString())
        ) {

            SubscriptionType subscriptionTypeALL =
                    subscriptionTypeService.findByName(SubscriptionTypeEnum.ALL.toString());
            SubscriptionType subscriptionTypeEDIT =
                    subscriptionTypeService.findByName(SubscriptionTypeEnum.EDIT.toString());
            SubscriptionType subscriptionTypeDELETE =
                    subscriptionTypeService.findByName(SubscriptionTypeEnum.DELETE.toString());

            resultEmailsList.addAll(
                    emailService.findBySubscriptionType(subscriptionTypeALL)
            );
            resultEmailsList.addAll(
                    emailService.findBySubscriptionType(subscriptionTypeEDIT)
            );
            resultEmailsList.addAll(
                    emailService.findBySubscriptionType(subscriptionTypeDELETE)
            );
        }
       return resultEmailsList;
    }
}
