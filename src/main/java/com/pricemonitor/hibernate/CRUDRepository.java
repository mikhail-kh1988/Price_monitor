package com.pricemonitor.hibernate;

import java.io.Serializable;

public interface CRUDRepository <T extends Serializable>{
    void create(T entity);
    void update(T entity);
    T findOneById(Integer id);
    java.util.List<T> findAll();
    void delete(T entity);
}
