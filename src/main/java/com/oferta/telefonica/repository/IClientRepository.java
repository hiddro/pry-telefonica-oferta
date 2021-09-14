package com.oferta.telefonica.repository;

import com.oferta.telefonica.model.entity.Cliente;
import com.oferta.telefonica.service.ICrudService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface IClientRepository extends JpaRepository<Cliente, Long> {

//    @Query("select c from clientes c left join fetch c.facturas f where c.id=?1")
    @Query("SELECT c FROM Cliente c WHERE c.typeDocument = ?1 AND c.numberDocument =?2")
    Optional<Cliente> validate(String typeDocument, String numberDocument);
}
