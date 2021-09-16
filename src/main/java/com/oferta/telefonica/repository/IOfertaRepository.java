package com.oferta.telefonica.repository;

import com.oferta.telefonica.model.dto.OfertaDto;
import com.oferta.telefonica.model.entity.Linea;
import com.oferta.telefonica.model.entity.Oferta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IOfertaRepository extends JpaRepository<Oferta, Long> {

    Oferta findByCodigoOferta(String codigoOferta);
}
