package com.oferta.telefonica.service;

import com.oferta.telefonica.model.entity.Cliente;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IClientService {

    public ResponseEntity<Cliente> saveClient(Cliente cliente);

    public ResponseEntity<Cliente> getClientById(Long id);

    public ResponseEntity<List<Cliente>> getAllClient();

    public ResponseEntity<Cliente> updateClient(Long id, Cliente cliente);

    public ResponseEntity<Cliente> deleteClient(Long id);
}
