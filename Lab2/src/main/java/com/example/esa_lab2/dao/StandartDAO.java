package com.example.esa_lab2.dao;

import java.util.List;
import java.util.UUID;

public interface StandartDAO<T> {
    List<T> selectAll();
    T select(UUID id);
    void insert(T entity);
    void update(T entity);
    void delete(T entity);
}
