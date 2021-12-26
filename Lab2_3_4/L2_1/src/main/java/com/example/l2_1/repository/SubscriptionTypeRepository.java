package com.example.l2_1.repository;

import com.example.l2_1.entity.SubscriptionType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SubscriptionTypeRepository extends JpaRepository<SubscriptionType, UUID> {
    SubscriptionType findByName(String name);
}
