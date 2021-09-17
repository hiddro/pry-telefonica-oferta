package com.oferta.telefonica.repository;

import com.oferta.telefonica.model.entity.Oferta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface IOfertaRepository extends JpaRepository<Oferta, Long> {

    Oferta findByCodigoOferta(String codigoOferta);

    @Query("select o from Oferta o where o.codigoOferta = ?1 ")
    Optional<Oferta> findByCodigo(String codigoOferta);
}
