package com.microserviceproject.services.client.controller;

import com.microserviceproject.services.client.domian.Client;
import com.microserviceproject.services.client.exeception.NoClientExistExeception;
import com.microserviceproject.services.client.repo.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RefreshScope
@RestController
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;

    @Value("${client-service.profileVariable}")
    private String profileVariable;

    @GetMapping(value = "/pathvariabele")
    public String getPathVariable() {
        return profileVariable;
    }

    @GetMapping(value = "/client")
    public List<Client> getClients() {
        return clientRepository.findAll();
    }

    @GetMapping(value = "/client/{id}")
    public Client getClient(@PathVariable Integer id) {
        Client oneClient = clientRepository.getOne(id);
        if (oneClient != null) {
            return oneClient;
        } else {
            throw new NoClientExistExeception("Client doesn't exist");
        }
    }

    @DeleteMapping(value = "/client/{id}")
    public void deleteClient(@PathVariable Integer id) {
        Client oneClient = clientRepository.getOne(id);
        if (oneClient != null) {
            clientRepository.deleteById(oneClient.getID());
        } else {
            throw new NoClientExistExeception("Client doesn't exist");
        }
    }

    @PutMapping(value = "/client/{id}")
    public Client updateClient(@PathVariable Integer id, @RequestBody @Validated Client client) {
        return clientRepository.findById(id)
                .map(oldClient -> {
                    return clientRepository.save(oldClient);
                })
                .orElseGet(() -> {
                    return clientRepository.save(client);
                });
    }

    @PostMapping(value = "/client")
    public Client createClient(@RequestBody @Validated Client client) {
        return clientRepository.saveAndFlush(client);
    }
}
