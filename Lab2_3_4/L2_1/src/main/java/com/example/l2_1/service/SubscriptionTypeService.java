package com.example.l2_1.service;


import com.example.l2_1.entity.SubscriptionType;
import com.example.l2_1.repository.SubscriptionTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubscriptionTypeService {

    @Autowired
    private SubscriptionTypeRepository subscriptionTypeRepository;


    public SubscriptionType findByName(String name) {
        return subscriptionTypeRepository.findByName(name);
    }

}
