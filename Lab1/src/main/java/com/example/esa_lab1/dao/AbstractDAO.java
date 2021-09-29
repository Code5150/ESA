package com.example.esa_lab1.dao;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.UUID;

public class AbstractDAO {
    @Getter
    @Setter
    protected static EntityManager em;

    protected static void flushAndClear() {
        em.flush();
        em.clear();
    }
}
