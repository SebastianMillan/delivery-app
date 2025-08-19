package com.deliverysl.luxurydelivery.utils;

import java.util.List;
import java.util.Optional;

public interface BaseService<T, ID> {
    T save(T entity);
    Optional<T> findOptionalById(ID id);
    boolean existsById(ID id);
    List<T> findAll();
    void deleteById(ID id);
    void delete(T t);
}
