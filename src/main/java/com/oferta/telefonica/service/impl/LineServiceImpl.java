package com.oferta.telefonica.service.impl;

import com.oferta.telefonica.model.entity.Cliente;
import com.oferta.telefonica.model.entity.Linea;
import com.oferta.telefonica.model.entity.Oferta;
import com.oferta.telefonica.repository.IClientRepository;
import com.oferta.telefonica.repository.ILineRepository;
import com.oferta.telefonica.repository.IOfertaRepository;
import com.oferta.telefonica.service.ILineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
public class LineServiceImpl implements ILineService {

    @Autowired
    private ILineRepository lineRepository;

    @Autowired
    private IClientRepository clientRepository;

    @Autowired
    private IOfertaRepository ofertaRepository;

    @Override
    public ResponseEntity<Linea> addLine(Linea linea) {
        Map<String, Object> response = new HashMap<>();

        Optional<Linea> getLinea = Optional.ofNullable(lineRepository.findByNroTelefono(linea.getNroTelefono())
                .orElse(Linea.builder().build()));

        if(getLinea.get().getIdLinea() != null){
            response.put("mensaje", "la linea con estos datos ya existe");
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }

        Linea line = lineRepository.save(linea);

        response.put("mensaje", "Se registró la linea");
        response.put("linea", line);

        return new ResponseEntity(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Linea> getLineById(Long id) {
        Map<String, Object> response = new HashMap<>();
        Optional<Linea> existLine = lineRepository.findById(id);
        return existLine.map(c -> {
            response.put("mensaje", "Se encontro la linea");
            response.put("linea", c);

            return new ResponseEntity(response, HttpStatus.OK);
        }).orElse(new ResponseEntity("no se encontro la linea", HttpStatus.BAD_REQUEST));
    }

    @Override
    public ResponseEntity<Linea> joinLineOferta(Long idL, Long idO) {
        Map<String, Object> response = new HashMap<>();
        List<Oferta> listaOferta = new ArrayList<>();

        Optional<Oferta> oferta = Optional.ofNullable(ofertaRepository.findById(idO).orElse(Oferta.builder().build()));
        Optional<Linea> linea = Optional.ofNullable(lineRepository.findById(idL).orElse(Linea.builder().build()));

        if (oferta.get().getIdOferta() == null || linea.get().getIdLinea() == null){
            return new ResponseEntity("Uno de los Id no existe en la Base de Datos", HttpStatus.BAD_REQUEST);
        }

        listaOferta.add(oferta.get());

        if(linea.get().getOfertas().size() == 0){
            linea.get().setOfertas(listaOferta);
        }else{
            linea.get().getOfertas().add(oferta.get());
        }

        lineRepository.save(linea.get());

        response.put("mensaje", "Se agrego la oferta a la linea");
        response.put("linea", linea.get());

        return new ResponseEntity(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Linea> getLineOfert(String type, String number) {
        Map<String, Object> response = new HashMap<>();

        Optional<Cliente> cliente = Optional.ofNullable(clientRepository.validate(type, number).orElse(Cliente.builder().build()));

        if(cliente.get().getIdCliente() == null){
            response.put("mensaje", "el cliente con estos datos no existe");
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }

        response.put("mensaje", "Las Lineas y Ofertas de " + cliente.get().getName());
        response.put("linea", cliente.get().getLineas());

        return new ResponseEntity(response, HttpStatus.OK);

    }


}
