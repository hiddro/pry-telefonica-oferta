package com.oferta.telefonica.service.impl;

import com.oferta.telefonica.model.entity.Cliente;
import com.oferta.telefonica.model.entity.Linea;
import com.oferta.telefonica.repository.IClientRepository;
import com.oferta.telefonica.repository.ILineRepository;
import com.oferta.telefonica.service.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class ClientServiceImpl implements IClientService {

    @Autowired
    private IClientRepository clientRepository;

    @Autowired
    private ILineRepository lineRepository;

    @Override
    public ResponseEntity<Cliente> saveClient(Cliente cliente) {
        Map<String, Object> response = new HashMap<>();

        Optional<Cliente> getClient = Optional.ofNullable(clientRepository.validate(cliente.getTypeDocument(), cliente.getNumberDocument())
                .orElse(Cliente.builder().build()));

        if(getClient.get().getId() != null){
            response.put("mensaje", "el cliente con estos datos ya existe");
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }

        Cliente createClient = clientRepository.save(cliente);
        response.put("mensaje", "Se registró el cliente correctamente");
        response.put("cliente", createClient);

        return new ResponseEntity(response, HttpStatus.OK);

    }

    @Override
    public ResponseEntity<Cliente> getClientById(Long id) {
        Map<String, Object> response = new HashMap<>();
        Optional<Cliente> existClient = clientRepository.findById(id);
        return existClient.map(c -> {
            response.put("mensaje", "Se encontro al cliente");
            response.put("cliente", c);

            return new ResponseEntity(response, HttpStatus.OK);
        }).orElse(new ResponseEntity("no se encontro al cliente", HttpStatus.BAD_REQUEST));
    }

    @Override
    public ResponseEntity<List<Cliente>> getAllClient() {
        Map<String, Object> response = new HashMap<>();
        List<Cliente> listClient = clientRepository.findAll();
//        List<Cliente> listaClient = clientRepository.findAll()
//                .stream()
//                .map(c -> {
//                    Optional<Linea> linea = lineRepository.findById(c.getId());
//                    List<Linea> lista = new ArrayList<>();
//                    Linea lin = new Linea();
//                    lin.setId(linea.get().getId());
//                    lin.setCliente(linea.get().getCliente());
//                    lin.setEstado(linea.get().getEstado());
//                    lin.setOfertas(linea.get().getOfertas());
//                    lin.setType(linea.get().getType());
//                    lin.setNombrePlan(linea.get().getNombrePlan());
//                    lin.setNroTelefono(linea.get().getNroTelefono());
//                    lista.add(lin);
//                    c.setLineas(lista);
//                    return c;
//                })
//                .collect(Collectors.toList());

        response.put("mensaje", "Se registró el cliente correctamente");
        response.put("cliente", listClient);

        return new ResponseEntity(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Cliente> updateClient(Long id, Cliente cliente) {
        Map<String, Object> response = new HashMap<>();
        Optional<Cliente> existClient = clientRepository.findById(id);

        if(!existClient.isPresent()){
            return new ResponseEntity("No se encontro el cliente", HttpStatus.BAD_REQUEST);
        }

        response.put("mensaje", "Se actualizo el cliente correctamente");
        response.put("cliente", cliente);
        cliente.setId(id);
        clientRepository.save(cliente);

        return new ResponseEntity(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Cliente> deleteClient(Long id) {
//        Map<String, Object> response = new HashMap<>();
//        return clientRepository.findById(id).map(c -> {
//            response.put("mensaje", "Se elimino el cliente correctamente");
//            response.put("cliente", c);
//
//            clientRepository.delete(c);
//            return new ResponseEntity(response, HttpStatus.OK);
//        }).orElse(new ResponseEntity("no se encontro al cliente", HttpStatus.BAD_REQUEST));
        return null;
    }


}
