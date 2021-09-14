package com.oferta.telefonica.service;

import com.oferta.telefonica.model.entity.Linea;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ILineService {

    public ResponseEntity<Linea> saveLine(Linea linea);

    public ResponseEntity<List<Linea>> getAllLine();
}
