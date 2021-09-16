package com.oferta.telefonica.service.impl;

import com.oferta.telefonica.model.entity.Cliente;
import com.oferta.telefonica.service.IReportService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportServiceImpl implements IReportService {

    @Override
    public ResponseEntity<List<Cliente>> getOfertaFechas(String fi) {
        System.out.println(fi + "asdasd");
        return null;
    }
}
