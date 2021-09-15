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

    @PostMapping("/addOfertaLine/{id}")
    public ResponseEntity<Oferta> addOferta(@Valid @PathVariable Long id, @Valid @RequestBody Oferta oferta){
        return ofertaService.addOferta(id, oferta);
    }
}
