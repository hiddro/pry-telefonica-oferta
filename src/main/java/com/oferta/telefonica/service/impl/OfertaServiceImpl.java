package com.oferta.telefonica.service.impl;

import com.oferta.telefonica.model.entity.Linea;
import com.oferta.telefonica.model.entity.Oferta;
import com.oferta.telefonica.repository.IOfertaRepository;
import com.oferta.telefonica.service.IOfertaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class OfertaServiceImpl implements IOfertaService {

    @Autowired
    private IOfertaRepository ofertaRepository;

    @Override
    public ResponseEntity<Oferta> addOferta(Long id, Oferta oferta) {
        Map<String, Object> response = new HashMap<>();
        Oferta ofer = ofertaRepository.save(oferta);

        response.put("mensaje", "Se registró la oferta");
        response.put("oferta", ofer);

        return new ResponseEntity(response, HttpStatus.OK);
    }
}
