package com.deliverysl.luxurydelivery.utils;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class BaseServiceImpl<T extends ActivableEntity,ID> implements BaseService<T, ID> {

    @Autowired
    protected BaseRepository<T, ID> repository;

    @Override
    public T save(T entity) {
        return repository.save(entity);
    }

    @Override
    public Optional<T> findOptionalById(ID id) {
        return repository.findById(id);
    }

    @Override
    public boolean existsById(ID id) {
        return repository.existsById(id);
    }

    @Override
    public List<T> findAll() {
        return repository.findAll();
    }

    @Override
    public void deleteById(ID id) {
        T entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entity Not Found"));
        entity.setActive(false);
        repository.save(entity);
    }

    @Override
    public void delete(T entity) {
        entity.setActive(false);
        repository.save(entity);
    }

    @Override
    public List<T> findAllByActiveTrue() {
        return repository.findAllByActiveTrue();
    }

    @Override
    public T deactivate(ID id) {
        T entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entity Not Found"));
        entity.setActive(!entity.isActive());
        repository.save(entity);
        return entity;
    }
}
