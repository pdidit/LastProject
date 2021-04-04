package com.microserviceproject.services.client.controller;

import com.microserviceproject.services.client.domian.Client;
import com.microserviceproject.services.client.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ClientController {

    @Autowired
    ClientService clientService;

    @GetMapping(value = "/Client")
    public List<Client> getClients() {
        return clientService.getClients();
    }

    @GetMapping(value = "/Client/{id}")
    public Client getClient(@PathVariable Integer id) {
        /*
        Client oneClient = ClientService.getClientById(id);
        if (oneClient != null) {
            return oneClient;
        } else {
            throw new RuntimeException("Client doesn't exist");
        }*/
        return clientService.getClientById(id);
    }

    @DeleteMapping(value = "/Client/{id}")
    public void deleteClient(@PathVariable Integer id) {
        clientService.deleteClient(id);
    }

    @PutMapping(value = "/Client/{id}")
    public Client updateClient(@PathVariable Integer id, @RequestBody Client Client) {
        return clientService.updateClient(Client, id);
    }

    @PostMapping(value = "/Client")
    public Client createClient(@RequestBody Client Client) {
        return clientService.createClient(Client);
    }
}
