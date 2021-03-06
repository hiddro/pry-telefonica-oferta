package com.oferta.telefonica.service;

import com.oferta.telefonica.model.entity.Cliente;
import org.springframework.http.ResponseEntity;

import java.text.ParseException;
import java.util.List;

public interface IReportService {

    public ResponseEntity<List<Cliente>> getOfertaFechas(String finic, String ffin) throws ParseException;
}
