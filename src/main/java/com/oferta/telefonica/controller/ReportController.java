package com.oferta.telefonica.controller;

import com.oferta.telefonica.model.entity.Cliente;
import com.oferta.telefonica.service.IClientService;
import com.oferta.telefonica.service.IReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/report")
public class ReportController {

    @Autowired
    private IReportService reportService;

    @GetMapping("/get/{fini}/{ffin}")
    public ResponseEntity<List<Cliente>> fechasRango(@PathVariable String fini, @PathVariable String ffin) {
        return reportService.getOfertaFechas(fini, ffin);
    }
}
