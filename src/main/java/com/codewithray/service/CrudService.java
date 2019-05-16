package com.codewithray.service;

import java.util.List;

public interface CrudService<T> {
    T add(T t);

    List<T> get(int page, int size);

    T getById(Long id);

    T update(T t);

    void delete(Long id);
}
