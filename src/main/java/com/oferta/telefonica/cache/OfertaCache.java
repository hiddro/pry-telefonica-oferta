package com.oferta.telefonica.cache;

import com.oferta.telefonica.model.entity.Oferta;
import com.oferta.telefonica.repository.IOfertaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OfertaCache {

    @Autowired
    private IOfertaRepository ofertaRepository;

    @Cacheable(value = "ofertaCache", key = "#codigoOferta")
    public Oferta getOferta(String codigoOferta){
        System.out.println("Obteniendo de DB Cache por el codigo oferta " + codigoOferta);
        return ofertaRepository.findByCodigoOferta(codigoOferta);
    }

    @Cacheable(value = "ofertaCache")
    public List<Oferta> getOfertaAll(){
        System.out.println("Obteniendo de DB Cache todo los catalogos");
        return ofertaRepository.findAll();
    }
}
