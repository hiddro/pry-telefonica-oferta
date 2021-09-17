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

import java.util.*;

@Service
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

        if(getClient.get().getIdCliente() != null){
            response.put("mensaje", "el cliente con estos datos ya existe");
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }

        Cliente createClient = clientRepository.save(cliente);
        response.put("mensaje", "Se registró el cliente correctamente");
        response.put("cliente", createClient);

        return new ResponseEntity(response, HttpStatus.OK);

    }

    @Override
    public ResponseEntity<Linea> joinClientLine(Long idC, Long idL) {
        Map<String, Object> response = new HashMap<>();
        List<Linea> listaLinea = new ArrayList<>();

        Optional<Cliente> cliente = Optional.ofNullable(clientRepository.findById(idC).orElse(Cliente.builder().build()));
        Optional<Linea> linea = Optional.ofNullable(lineRepository.findById(idL).orElse(Linea.builder().build()));

        if (cliente.get().getIdCliente() == null || linea.get().getIdLinea() == null){
            return new ResponseEntity("Uno de los Id no existe en la Base de Datos", HttpStatus.BAD_REQUEST);
        }

        listaLinea.add(linea.get());

        if(cliente.get().getLineas().size() == 0){
            cliente.get().setLineas(listaLinea);
        }else{
            cliente.get().getLineas().add(linea.get());
        }

        clientRepository.save(cliente.get());

        response.put("mensaje", "Se agrego la linea" + linea.get().getNroTelefono() + " al cliente " + cliente.get().getName());
        response.put("cliente", cliente.get());

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

        response.put("mensaje", "Se encontro todos los clientes correctamente");
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
        cliente.setIdCliente(id);
        clientRepository.save(cliente);

        return new ResponseEntity(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Cliente> deleteClient(Long id) {
        Map<String, Object> response = new HashMap<>();
        return clientRepository.findById(id).map(c -> {
            response.put("mensaje", "Se elimino el cliente correctamente");
            response.put("cliente", c);

            clientRepository.delete(c);
            return new ResponseEntity(response, HttpStatus.OK);
        }).orElse(new ResponseEntity("no se encontro al cliente", HttpStatus.BAD_REQUEST));
    }

}
