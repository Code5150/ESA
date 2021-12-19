package com.example.l2_1.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="log")
public class Log extends BaseUuidEntity {

    @Column(name = "kind_change")
    @Getter
    @Setter
    private String kindChange;

    @Column(name = "entity")
    @Getter
    @Setter
    private String entity;

    @Column(name = "details")
    @Getter
    @Setter
    private String details;

    @Override
    public String toString() {
        return "Log{" +
                "kindChange='" + kindChange + '\'' +
                ", entity='" + entity + '\'' +
                ", details='" + details + '\'' +
                '}';
    }
}
