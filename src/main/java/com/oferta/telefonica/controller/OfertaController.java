package com.oferta.telefonica.controller;

import com.oferta.telefonica.model.entity.Linea;
import com.oferta.telefonica.model.entity.Oferta;
import com.oferta.telefonica.service.IOfertaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/oferta")
public class OfertaController {

    @Autowired
    private IOfertaService ofertaService;

//    @PostMapping("/addOfertaLine/{idL}/{idO}") @Valid @PathVariable Long id,
    @PostMapping("/saveOffert")
    public ResponseEntity<Oferta> addOferta(@Valid @RequestBody Oferta oferta){
        return ofertaService.addOferta(oferta);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Oferta> getOfertaById(@Valid @PathVariable Long id){
        return ofertaService.getOfertaById(id);
    }
}
