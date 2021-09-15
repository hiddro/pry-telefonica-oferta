package com.oferta.telefonica.repository;

import com.oferta.telefonica.model.entity.Linea;
import com.oferta.telefonica.model.entity.Oferta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOfertaRepository extends JpaRepository<Oferta, Long> {
}
