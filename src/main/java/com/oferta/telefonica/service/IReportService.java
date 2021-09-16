package com.oferta.telefonica.service;

import com.oferta.telefonica.model.entity.Cliente;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IReportService {

    public ResponseEntity<List<Cliente>> getOfertaFechas(String fi);
}
