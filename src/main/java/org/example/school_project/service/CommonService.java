package org.example.school_project.service;

import java.util.List;

public interface CommonService<T> {
    T getById(Long id);
    T getByUsername(String username);
    T getByEmail(String mail);
    void create(T object);
    void delete(Long id);
    T update(T object);
    List<T> getAll();
}
