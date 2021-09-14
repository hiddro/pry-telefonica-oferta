package com.oferta.telefonica.service.impl;

import com.oferta.telefonica.model.entity.Cliente;
import com.oferta.telefonica.repository.IClientRepository;
import com.oferta.telefonica.service.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ClientServiceImpl implements IClientService {

    @Autowired
    private IClientRepository clientRepository;

    @Override
    public ResponseEntity<Cliente> saveClient(Cliente cliente) {
        Map<String, Object> response = new HashMap<>();
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
        Iterable<Cliente> listClient = clientRepository.findAll();

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
        Map<String, Object> response = new HashMap<>();
        return clientRepository.findById(id).map(c -> {
            response.put("mensaje", "Se elimino el cliente correctamente");
            response.put("cliente", c);

            clientRepository.deleteById(c.getId());
            return new ResponseEntity(response, HttpStatus.OK);
        }).orElse(new ResponseEntity("no se encontro al cliente", HttpStatus.BAD_REQUEST));
    }


}
