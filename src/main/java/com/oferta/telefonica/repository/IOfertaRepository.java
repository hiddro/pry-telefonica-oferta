package com.oferta.telefonica.repository;

import com.oferta.telefonica.model.entity.Cliente;
import com.oferta.telefonica.model.entity.Oferta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface IOfertaRepository extends JpaRepository<Oferta, Long> {

    Oferta findByCodigoOferta(String codigoOferta);

    @Query("select o from Oferta o where o.codigoOferta = ?1 ")
    Optional<Oferta> findByCodigo(String codigoOferta);

    @Query("SELECT o FROM Oferta o WHERE (o.startOferta BETWEEN ?1 AND ?2) AND (o.endOferta BETWEEN ?1 AND ?2)")
    Optional<List<Oferta>> findByRangoFechas(Date finic, Date ffin);
}
