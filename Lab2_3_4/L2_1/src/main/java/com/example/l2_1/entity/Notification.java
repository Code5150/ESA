package com.example.l2_1.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="notification")
public class Notification extends BaseUuidEntity{

    @Column(name = "from_email")
    @Getter
    @Setter
    private String fromEmail;

    @Column(name = "to_email")
    @Getter
    @Setter
    private String toEmail;

    @Column(name = "theme")
    @Getter
    @Setter
    private String theme;

    @Column(name = "description")
    @Getter
    @Setter
    private String description;

}
