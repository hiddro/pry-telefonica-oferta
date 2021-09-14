package com.oferta.telefonica.controller;

import com.oferta.telefonica.model.entity.Cliente;
import com.oferta.telefonica.model.entity.Linea;
import com.oferta.telefonica.service.ILineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/line")
public class LineaController {

    @Autowired
    private ILineService lineService;

    @PostMapping("/saveClient")
    public ResponseEntity<Linea> saveLine(@Valid @RequestBody Linea linea){
        return lineService.saveLine(linea);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Linea> getClientById(@Valid @PathVariable Long id){
        return lineService.getLineById(id);
    }
}
