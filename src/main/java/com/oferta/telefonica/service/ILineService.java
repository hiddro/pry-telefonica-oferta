package com.oferta.telefonica.service;

import com.oferta.telefonica.model.entity.Linea;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ILineService {

    public ResponseEntity<Linea> addLine(Linea linea);

    public ResponseEntity<Linea> getLineById(Long id);

    public ResponseEntity<Linea> joinLineOferta(Long idL, Long idO);

    public ResponseEntity<Linea> getLineOfert(String type, String number);
}
