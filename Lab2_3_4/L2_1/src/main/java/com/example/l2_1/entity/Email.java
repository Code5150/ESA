package com.example.l2_1.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="email")
public class Email extends BaseUuidEntity {

    @Column(name = "to")
    @Getter
    @Setter
    private String to;

    @Column(name = "body")
    @Getter
    @Setter
    private String body;

    @Override
    public String toString() {
        return "Email{" +
                "to='" + to + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
