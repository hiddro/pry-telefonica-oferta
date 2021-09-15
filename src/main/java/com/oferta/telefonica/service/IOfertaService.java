package com.oferta.telefonica.service;

import com.oferta.telefonica.model.entity.Linea;
import com.oferta.telefonica.model.entity.Oferta;
import org.springframework.http.ResponseEntity;

public interface IOfertaService {

    public ResponseEntity<Oferta> addOferta(Oferta oferta);

    public ResponseEntity<Oferta> getOfertaById(Long id);

}