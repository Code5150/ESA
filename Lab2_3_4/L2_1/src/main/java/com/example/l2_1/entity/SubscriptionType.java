package com.example.l2_1.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="subscription_type")
public class SubscriptionType {

    @Id
    @Column(name = "id")
    @Getter
    @Setter
    private Integer id;

    // имеет одно из следующих значений:
    // ALL (получает все сообщения)
    // EDIT (получает сообщения, если только были внесены изменения в БД)
    // DELETE (получает сообщения, если только были удалены записи в таблицах)
    @Column(name = "name")
    @Getter
    @Setter
    private String name;


    @Override
    public String toString() {
        return "SubscriptionType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
