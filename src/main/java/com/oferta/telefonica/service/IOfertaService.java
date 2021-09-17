package com.oferta.telefonica.service;

import com.oferta.telefonica.model.entity.Cliente;
import com.oferta.telefonica.model.entity.Linea;
import com.oferta.telefonica.model.entity.Oferta;
import org.springframework.http.ResponseEntity;

import java.text.ParseException;
import java.util.List;

public interface IOfertaService {

    public ResponseEntity<Oferta> addOferta(Oferta oferta);
}
