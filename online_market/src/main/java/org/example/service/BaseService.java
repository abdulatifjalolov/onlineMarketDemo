package org.example.service;

public interface BaseService<T, R> {
    R add(T t);

    boolean delete(int id);

    R getById(int id);

    R update(T t);
}
