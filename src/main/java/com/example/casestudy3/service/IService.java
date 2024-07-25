package com.example.casestudy3.service;

import java.util.List;
import java.util.UUID;

public interface IService<T> {
    T create(T t);
    T update(T t, UUID id);
    List<T> getAll();
    T findById(UUID id);
    Boolean delete(UUID id);
    List<T> findByName(String name);
}
