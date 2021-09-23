package com.oferta.telefonica.service.impl;

import com.oferta.telefonica.model.entity.Cliente;
import com.oferta.telefonica.model.entity.Linea;
import com.oferta.telefonica.model.entity.Oferta;
import com.oferta.telefonica.repository.IClientRepository;
import com.oferta.telefonica.repository.IOfertaRepository;
import com.oferta.telefonica.service.IReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ReportServiceImpl implements IReportService {

    @Autowired
    private IClientRepository clientRepository;

    @Autowired
    private IOfertaRepository ofertaRepository;

    @Override
    public ResponseEntity<List<Cliente>> getOfertaFechas(String finic, String ffin) throws ParseException {
        Map<String, Object> response = new HashMap<>();

        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        Date finicial = formato.parse(finic);
        Date ffinal = formato.parse(ffin);

        Optional<List<Oferta>> total = ofertaRepository.findByRangoFechas(finicial, ffinal);

        List<Cliente> clientesOfertas = clientRepository.findAll()
                .stream()
                .filter(c -> {
                    int contador = 0;
                    for (Linea x : c.getLineas()){
                        if(x.getEstado().equals("Activo")){
                            contador++;
                        }
                    }
                    return contador >= 3;
                })
                .map(d -> {

                    /*Creando el Objeto*/
                    Cliente clt = new Cliente();
                    Linea lna = new Linea();
                    List<Linea> listaLna = new ArrayList<>();
                    Oferta oft = new Oferta();
                    List<Oferta> listaOft = new ArrayList<>();

                    /*Replica del Cliente*/
                    clt.setIdCliente(d.getIdCliente());
                    clt.setName(d.getName());
                    clt.setTypeDocument(d.getTypeDocument());
                    clt.setNumberDocument(d.getNumberDocument());
                    clt.setCreateClient(d.getCreateClient());

                    for (Linea x: d.getLineas()){
                        for (Oferta y: x.getOfertas()){
                            for (Oferta z: total.get()){
                                if(y.getIdOferta().equals(z.getIdOferta())){
                                    /*Replica de la Linea*/
                                    lna.setIdLinea(x.getIdLinea());
                                    lna.setNroTelefono(x.getNroTelefono());
                                    lna.setEstado(x.getEstado());
                                    lna.setType(x.getType());
                                    lna.setNombrePlan(x.getNombrePlan());
                                    lna.setHaveClient(x.getHaveClient());
                                    lna.setCreateLine(x.getCreateLine());
                                    listaLna.add(lna);
                                    clt.setLineas(listaLna);

                                    /*Replicando la Oferta*/
                                    oft.setIdOferta(y.getIdOferta());
                                    oft.setCodigoOferta(y.getCodigoOferta());
                                    oft.setDescripcion(y.getDescripcion());
                                    oft.setHaveLine(y.getHaveLine());
                                    oft.setStartOferta(y.getStartOferta());
                                    oft.setEndOferta(y.getEndOferta());
                                    listaOft.add(oft);
                                    lna.setOfertas(listaOft);
                                }
                            }
                        }
                    }

                    return clt;
                })
                .collect(Collectors.toList());

        response.put("mensaje", "Se obtuvo los registros");
        response.put("reporte", clientesOfertas);

        return new ResponseEntity(response, HttpStatus.OK);
    }

}
