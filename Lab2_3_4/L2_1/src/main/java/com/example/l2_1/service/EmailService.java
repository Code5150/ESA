package com.example.l2_1.service;


import com.example.l2_1.entity.Email;
import com.example.l2_1.entity.SubscriptionType;
import com.example.l2_1.repository.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailService {

    @Autowired
    private EmailRepository emailRepository;

    public List<Email> findBySubscriptionType(SubscriptionType subscriptionType) {
        return emailRepository.findBySubscriptionType(subscriptionType);
    }

}
