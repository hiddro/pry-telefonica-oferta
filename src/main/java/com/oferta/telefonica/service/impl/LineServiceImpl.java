package com.oferta.telefonica.service.impl;

import com.oferta.telefonica.model.entity.Linea;
import com.oferta.telefonica.repository.ILineRepository;
import com.oferta.telefonica.service.ILineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
public class LineServiceImpl implements ILineService {

    @Autowired
    private ILineRepository lineRepository;

    @Override
    public ResponseEntity<Linea> saveLine(Linea linea) {
        Map<String, Object> response = new HashMap<>();
        Linea line = lineRepository.save(linea);

        response.put("mensaje", "Se registró la linea");
        response.put("cliente", line);

        return new ResponseEntity(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Linea> getLineById(Long id) {
        return null;
    }


}
