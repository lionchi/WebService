package com.gavrilov.dao;

import java.util.List;

public interface DAO<T> {
    T findById(Long id);

    List<T> findAll();

    void create(T entity);

    T update(T entity);

   void deleteById(Long id);
}
