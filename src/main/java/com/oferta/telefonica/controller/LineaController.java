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


    @PostMapping("/addLine")
    public ResponseEntity<Linea> addLineClient(@Valid @RequestBody Linea linea){
        return lineService.addLine(linea);
    }

    @GetMapping("/searcLine/{id}")
    public ResponseEntity<Linea> getLineById(@Valid @PathVariable Long id){
        return lineService.getLineById(id);
    }

    @PostMapping("/associar/{idL}/{idO}")
    public ResponseEntity<Linea> joinLineOferta(@Valid @PathVariable Long idL, @Valid @PathVariable Long idO){
        return lineService.joinLineOferta(idL, idO);
    }

    @GetMapping("/get/{type}/{number}")
    public ResponseEntity<Linea> getReturnLineOfert(@Valid @PathVariable String type, @Valid @PathVariable String number){
        return lineService.getLineOfert(type, number);
    }

    @PutMapping("/changePlan/{id}")
    public ResponseEntity<Linea> getChangePlan(@Valid @PathVariable("id") Long id, @Valid @RequestBody Linea linea){
        return lineService.getChangePlan(id, linea);
    }

    @PutMapping("/changeState/{id}")
    public ResponseEntity<Linea> getChangeState(@Valid @PathVariable("id") Long id){
        return lineService.getChangeState(id);
    }
}
