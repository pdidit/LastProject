package com.microserviceproject.services.client.service;

import com.microserviceproject.services.client.domian.Client;
import com.microserviceproject.services.client.repo.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public List<Client> getClients() {
        return clientRepository.findAll();
    }

    public Client getClientById(Integer id) {
        return clientRepository.getOne(id);   }

    public Client createClient(Client Client) {
        return clientRepository.saveAndFlush(Client);
    }

    public Client updateClient(Client client, Integer id) {
        return clientRepository.findById(id)
                .map(oldClient -> {;
                    return clientRepository.save(oldClient);
                })
                .orElseGet(() -> {
                    return clientRepository.save(client);
                });
    }

    public void deleteClient(Integer id) {
        clientRepository.findById(id).ifPresent(Client -> clientRepository.deleteById(2));
    }
}
