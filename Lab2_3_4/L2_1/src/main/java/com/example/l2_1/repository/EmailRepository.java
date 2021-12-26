package com.example.l2_1.repository;

import com.example.l2_1.entity.Email;
import com.example.l2_1.entity.SubscriptionType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface EmailRepository extends JpaRepository<Email, UUID> {
    List<Email> findBySubscriptionType(SubscriptionType subscriptionType);
}
