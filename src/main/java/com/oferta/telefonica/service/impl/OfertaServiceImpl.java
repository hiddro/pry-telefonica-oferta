package com.oferta.telefonica.service.impl;

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
        Optional<Oferta> oferExist = Optional.ofNullable(ofertaRepository.findByCodigo(oferta.getCodigoOferta())
                .orElse(Oferta.builder().build()));

        if(oferExist.get().getIdOferta() != null){
            return new ResponseEntity("La oferta ya existe", HttpStatus.OK);
        }

        Oferta ofert = ofertaRepository.save(oferta);

        response.put("mensaje", "Se registró la oferta");
        response.put("oferta", ofert);

        return new ResponseEntity(response, HttpStatus.OK);
    }
}
