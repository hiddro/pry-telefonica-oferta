package com.oferta.telefonica.repository;

import com.oferta.telefonica.model.entity.Cliente;
import org.springframework.data.repository.CrudRepository;

public interface IClientRepository extends CrudRepository<Cliente, Long> {
}
