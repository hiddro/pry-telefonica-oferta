package com.oferta.telefonica.service.impl;

import com.oferta.telefonica.model.entity.Linea;
import com.oferta.telefonica.model.entity.Oferta;
import com.oferta.telefonica.repository.ILineRepository;
import com.oferta.telefonica.repository.IOfertaRepository;
import com.oferta.telefonica.service.IOfertaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OfertaServiceImpl implements IOfertaService {

    @Autowired
    private IOfertaRepository ofertaRepository;

    @Autowired
    private ILineRepository lineRepository;

    @Override
    public ResponseEntity<Oferta> addOferta(Oferta oferta) {
        Map<String, Object> response = new HashMap<>();

        Oferta ofert = ofertaRepository.save(oferta);

        response.put("mensaje", "Se registró la oferta");
        response.put("oferta", ofert);

        return new ResponseEntity(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Oferta> getOfertaById(Long id) {
        Map<String, Object> response = new HashMap<>();
        Optional<Oferta> existOferta = ofertaRepository.findById(id);
        return existOferta.map(c -> {
            response.put("mensaje", "Se encontro la oferta");
            response.put("oferta", c);

            return new ResponseEntity(response, HttpStatus.OK);
        }).orElse(new ResponseEntity("no se encontro la oferta", HttpStatus.BAD_REQUEST));
    }
}
