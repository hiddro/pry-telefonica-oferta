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


    @PostMapping("/addLine/{id}")
    public ResponseEntity<Linea> addLineClient(@Valid @PathVariable Long id, @Valid @RequestBody Linea linea){
        return lineService.addLine(id, linea);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Linea> getLineById(@Valid @PathVariable Long id){
        return lineService.getLineById(id);
    }
}
