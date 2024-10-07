package org.example.school_project.service;

import java.util.List;

public interface CommonService<T> {
    T getById(Long id);
    void create(T object);
    void delete(Long id);
    T update(T object);
    List<T> getAll();
}
