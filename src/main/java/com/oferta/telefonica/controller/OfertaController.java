package com.oferta.telefonica.controller;

//import com.oferta.telefonica.cache.OfertaCache;
import com.oferta.telefonica.model.dto.OfertaDto;
import com.oferta.telefonica.model.entity.Cliente;
import com.oferta.telefonica.model.entity.Linea;
import com.oferta.telefonica.model.entity.Oferta;
import com.oferta.telefonica.service.IOfertaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("api/oferta")
public class OfertaController {

    @Autowired
    private IOfertaService ofertaService;

//    @Autowired
//    OfertaCache ofertaCache;

    @PostMapping("/saveOffert")
    public ResponseEntity<Oferta> addOferta(@Valid @RequestBody Oferta oferta){
        return ofertaService.addOferta(oferta);
    }

//    @GetMapping("/{codigoOferta}")
//    public OfertaDto getOferta(@PathVariable final String codigoOferta){
//        return ofertaCache.getOferta(codigoOferta);
//    }
}
