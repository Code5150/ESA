package com.example.l2_1.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="email")
public class Email extends BaseUuidEntity {

    @Column(name = "to_email")
    @Getter
    @Setter
    private String toEmail;

    @Column(name = "body")
    @Getter
    @Setter
    private String body;

    @Override
    public String toString() {
        return "Email{" +
                "to='" + toEmail + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
