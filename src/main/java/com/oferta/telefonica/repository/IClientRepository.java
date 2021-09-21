package com.oferta.telefonica.repository;

import com.oferta.telefonica.model.entity.Cliente;
import com.oferta.telefonica.service.ICrudService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface IClientRepository extends JpaRepository<Cliente, Long> {

    @Query("SELECT c FROM Cliente c WHERE c.typeDocument = ?1 AND c.numberDocument = ?2")
    Optional<Cliente> validate(String typeDocument, String numberDocument);

//    @Query("select c from Cliente c \n" +
//            "inner join customer_line cl on c.id_cliente = cl.id_cliente \n" +
//            "inner join Linea l on cl.id_linea = l.id_linea \n" +
//            "inner JOIN line_offer lo on l.id_linea = lo.id_linea \n" +
//            "INNER JOIN Oferta o2 on lo.id_oferta  = o2.id_oferta \n" +
//            "where (o2.start_oferta >= ?1 AND o2.end_oferta <= ?2) AND l.estado = 'Activo'")
//    Optional<List<Cliente>> findByRangoFechas(String finic, String ffin);
}
