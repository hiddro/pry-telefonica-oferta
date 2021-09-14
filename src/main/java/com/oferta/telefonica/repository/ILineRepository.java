package com.oferta.telefonica.repository;

import com.oferta.telefonica.model.entity.Linea;
import org.springframework.data.repository.CrudRepository;

public interface ILineRepository extends CrudRepository<Linea, Long> {
}
