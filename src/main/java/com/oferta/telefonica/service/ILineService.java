package com.oferta.telefonica.service;

import com.oferta.telefonica.model.entity.Linea;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ILineService {

    public ResponseEntity<Linea> addLine(Long id, Linea linea);

    public ResponseEntity<Linea> getLineById(Long id);

    public ResponseEntity<Linea> joinLineOferta(Long idL, Long idO);
}
