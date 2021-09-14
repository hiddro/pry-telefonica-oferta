package com.oferta.telefonica.service;

import com.oferta.telefonica.model.entity.Cliente;

import java.util.List;
import java.util.Optional;

public interface ICrudService<T,ID> {
    Optional create(T o);

    List<Optional> findAll();

    Optional findById(ID id);

    Optional update(T o);

    Optional<Void> delete(T o);
}
