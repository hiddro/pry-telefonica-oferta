package com.oferta.telefonica.cache;

import com.oferta.telefonica.model.entity.Oferta;
import com.oferta.telefonica.repository.IOfertaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
public class OfertaCache {

    @Autowired
    private IOfertaRepository ofertaRepository;

    @Cacheable(value = "ofertaCache", key = "#codigoOferta")
    public Oferta getOferta(String codigoOferta){
        System.out.println("Obteniendo de DB Cache por el codigo oferta " + codigoOferta);
        return ofertaRepository.findByCodigoOferta(codigoOferta);
    }
}
