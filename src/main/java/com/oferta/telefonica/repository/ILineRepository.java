package com.oferta.telefonica.repository;

import com.oferta.telefonica.model.entity.Linea;
import com.oferta.telefonica.service.ICrudService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface ILineRepository extends JpaRepository<Linea, Long> {
}
