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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ReportServiceImpl implements IReportService {

    @Autowired
    private IClientRepository clientRepository;

    @Autowired
    private IOfertaRepository ofertaRepository;

    @Override
    public ResponseEntity<List<Cliente>> getOfertaFechas(String finic, String ffin) {
        Map<String, Object> response = new HashMap<>();

        List<Long> ofertas = ofertaRepository.findAll()
                .stream()
                .filter(c -> {
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    String date = format.format(c.getStartOferta());
                    return (
                            compareDate(date, finic) > -1 && compareDate(date, ffin) < 1
                    );
                })
                .map(Oferta::getIdOferta)
                .collect(Collectors.toList());

        List<Cliente> cli =  clientRepository
                .findAll()
                .stream()
                .filter(cliente-> cliente.getLineas().size() >= 3)
                .filter(pl ->
                        !pl
                                .getLineas()
                                .stream()
                                .filter(opl -> opl.getEstado().equals("Activo"))
                                .filter(opl -> opl.getOfertas().size() > 0)
                                .filter(opl -> {
                                    return !opl
                                            .getOfertas()
                                            .stream()
                                            .filter(off -> {
                                                return !ofertas
                                                        .stream()
                                                        .filter(ofertaid -> ofertaid == off.getIdOferta())
                                                        .collect(Collectors.toList())
                                                        .isEmpty();
                                            })
                                            .collect(Collectors.toSet())
                                            .isEmpty();
                                })
                                .collect(Collectors.toList())
                                .isEmpty()
                )
                .collect(Collectors.toList());

        response.put("mensaje", "Se obtuvo los registros");
        response.put("reporte", cli);

        return new ResponseEntity(response, HttpStatus.OK);
    }

    public static int compareDate(String dat1, String dat2) {
        try {
            SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");

            Date compa1 = format1.parse(dat1);
            Date compa2 = format1.parse(dat2);

            return compa1.compareTo(compa2);
        } catch (Exception e) {
            return 0;
        }
    }
}
