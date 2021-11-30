package com.example.esa_lab2.dao;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.EntityManager;

public class AbstractDAO {
    @Getter
    @Setter
    protected static EntityManager em;

    protected static void flushAndClear() {
        em.flush();
        em.clear();
    }
}
