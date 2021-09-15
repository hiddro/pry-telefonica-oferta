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
//        List<Oferta> listaOferta = new ArrayList<>();
//
//        Optional<Linea> linea = Optional.ofNullable(lineRepository.findById(id).orElse(Linea.builder().build()));
//
//        if(linea.get().getIdLinea() == null){
//            response.put("mensaje", "la linea con estos datos no existe");
//            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
//        }

        Oferta ofert = ofertaRepository.save(oferta);
//        listaOferta.add(ofert);
//
//        linea.get().setOfertas(listaOferta);
//        lineRepository.save(linea.get());

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
