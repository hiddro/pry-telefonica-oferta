package com.oferta.telefonica.controller;

import com.oferta.telefonica.model.entity.Cliente;
import com.oferta.telefonica.service.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.*;

@RestController
@RequestMapping("api/client")
public class ClientController {

    @Autowired
    private IClientService clientService;

    @PostMapping("/saveClient")
    public ResponseEntity<Cliente> saveClient(@Valid @RequestBody Cliente client){
        return clientService.saveClient(client);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getClientById(@Valid @PathVariable Long id){
        return clientService.getClientById(id);
    }

    @GetMapping("/searchAll")
    public ResponseEntity<List<Cliente>> getAllClient(){
        return clientService.getAllClient();
    }

    @PutMapping("/updateClient/{id}")
    public ResponseEntity<Cliente> updateClient(@Valid @PathVariable("id") Long id, @Valid @RequestBody Cliente client){
        return clientService.updateClient(id, client);
    }

    @DeleteMapping("/deleteClient/{id}")
    public ResponseEntity<Cliente> deleteClient(@Valid @PathVariable("id") Long id){
        return clientService.deleteClient(id);
    }


}
