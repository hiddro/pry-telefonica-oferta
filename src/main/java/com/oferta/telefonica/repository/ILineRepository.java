package com.oferta.telefonica.repository;

import com.oferta.telefonica.model.entity.Cliente;
import com.oferta.telefonica.model.entity.Linea;
import com.oferta.telefonica.service.ICrudService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface ILineRepository extends JpaRepository<Linea, Long> {

    @Query("SELECT l FROM Linea l WHERE l.nroTelefono = ?1")
    Optional<Linea> findByNroTelefono(String nroTelefono);

}
