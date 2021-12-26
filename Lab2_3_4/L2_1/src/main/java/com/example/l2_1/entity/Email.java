package com.example.l2_1.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="email")
public class Email extends BaseUuidEntity {

    @Column(name = "name")
    @Getter
    @Setter
    private String name;


    @ManyToOne
    @Getter
    @Setter
    private SubscriptionType subscriptionType;

    @Override
    public String toString() {
        return "Email{" +
                "toEmail='" + name + '\'' +
                ", subscriptionType=" + subscriptionType.toString() +
                '}';
    }
}
