package com.microserviceproject.services.client.controller;

import com.microserviceproject.services.client.domian.Client;
import com.microserviceproject.services.client.domain.ContactBy;
import com.microserviceproject.services.client.dto.DTOClient;
import com.microserviceproject.services.client.exeception.NoClientExistExeception;
import com.microserviceproject.services.client.repo.ClientRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * This is the controller class for the client service.
 */
@RefreshScope
@RestController
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;

    //Path variable that is filled on startup by config server.
    @Value("${client-service.profile-variable1}")
    private String profileVariable1;
    //Path variable that is filled on startup by config server.
    @Value("${client-service.profile-variable2}")
    private String profileVariable2;

    /**
     * This is method that will return the path variables
     * @return String A strng that contains the Path variables for client service.
     */
    @GetMapping(value = "/pathvariabele")
    public String getPathVariable() {
        return "Profile parameter 1: " + profileVariable1 +
                "\nProfile parameter 2: " + profileVariable2 ;
    }

    /**
     * This is a endpoint to return a list of clients in the system
     * @return List<Client> A list of client object that are in the system
     */
    @GetMapping(value = "/client")
    public List<DTOClient> getClients() {

        //query the client objects and convert to DTOClient to return in the list
        List<DTOClient> allTransClient = new ArrayList<>();
        clientRepository.findAll().forEach(client -> {
            DTOClient trans = new DTOClient();
            BeanUtils.copyProperties(client, trans);
            allTransClient.add(trans);
        });

        return allTransClient;
    }

    /**
     * Get single client that is requested
     * @param id ID of the requested client.
     * @return DTOClient A client object of requested ID
     */
    @GetMapping(value = "/client/{id}")
    public DTOClient getClient(@PathVariable Integer id) {
        return clientRepository.findById(id).map(client -> {
            DTOClient outClient = new DTOClient();
            BeanUtils.copyProperties(client, outClient );
            return outClient;
        }).orElseThrow(() -> new NoClientExistExeception("Client doesn't exist"));
    }

    /**
     * Endpoint to delete a client from the system
     * @param id Integer ID of the client that is being deleted.
     */
    @DeleteMapping(value = "/client/{id}")
    public void deleteClient(@PathVariable Integer id) {
        //will confirm that client exist in the system
        //if not throw an error.
        Optional<Client> oneClient = clientRepository.findById(id);
        if (oneClient.isPresent()) {
            clientRepository.deleteById(oneClient.get().getID());
        } else {
            throw new NoClientExistExeception("Client doesn't exist");
        }
    }

    /**
     * Update a Client in the system
     * @param id Integer ID of the user that is being updated.
     * @param client DTOCLient Body of the client information being updated.
     * @return DTOClient Returns the new DTOClient that is created.
     */
    @PutMapping(value = "/client/{id}")
    public DTOClient updateClient(@PathVariable Integer id, @RequestBody @Valid DTOClient client) {
        //find the client and then convert to a eneity bean to be saved.
        Client updateClient = clientRepository.findById(id)
            .map(oldClient -> {
                LocalDate createdDate = oldClient.getCreationDate();
                BeanUtils.copyProperties(client, oldClient);
                oldClient.setID(id);
                //update the last changed date.
                oldClient.setLastUpdated(LocalDate.now());
                //ensures creation date is not changed.
                oldClient.setCreationDate(createdDate);
                return clientRepository.saveAndFlush(oldClient);
            })
            //if not exist create a new client object.
            .orElseGet(() -> {
                Client saveClient = new Client();
                BeanUtils.copyProperties(client, saveClient);
                //put the new creation date and last changed date.
                saveClient.setCreationDate(LocalDate.now());
                saveClient.setLastUpdated(LocalDate.now());
                return clientRepository.saveAndFlush(saveClient);
            });
        //returns the new client that was created
        DTOClient returnClient = new DTOClient();
        BeanUtils.copyProperties(updateClient,returnClient);
        return returnClient;
    }

    /**
     * Endpoint to create a new Client
     * @param client DTOClient The body of the new client created
     * @return DTOCLient Return the newly created object via a DTO.
     */
    @PostMapping(value = "/client")
    public DTOClient createClient(@RequestBody @Valid DTOClient client) {
        //copies the DTO to a Entity
        Client savingClient = new Client();
        BeanUtils.copyProperties(client, savingClient );
        savingClient.setID(null);
        //set a creation date and last updated date.
        savingClient.setLastUpdated(LocalDate.now());
        savingClient.setCreationDate(LocalDate.now());
        Client newClient = clientRepository.saveAndFlush(savingClient);

        //return the new create client
        DTOClient returnClient = new DTOClient();
        BeanUtils.copyProperties(newClient, returnClient );
        return returnClient;
    }

    /**
     * endpoint to search unsg the person first name and surname
     * @param firstName String First name of client being searched. Uses Request parameters
     * @param surname String Surname of client being searched. Uses Request parameters
     * @return DTOClient Returns list of the possible searches.
     */
    @GetMapping(value = "/client/search/name")
    public List<DTOClient> getClientSearch(@RequestParam String firstName, @RequestParam String surname) {
        //uses derived query to get results.
        List<DTOClient> resultClient = new ArrayList<>();
        clientRepository.findByFirstNameAndSurname(firstName,surname).forEach(client -> {
            //convert entity to a DTO object.
            DTOClient trans = new DTOClient();
            BeanUtils.copyProperties(client, trans);
            resultClient.add(trans);
        });

        return resultClient;
    }

    /**
     * Endpoint to search a list of the different contactby types.
     * @param contactBy ContactBy Give the parameter to serach for.
     * @return List<DTOClient> returns a list of the clients being searched.
     */
    @GetMapping(value = "/client/search/contact")
    public List<DTOClient> getSearchContactBy(@RequestParam ContactBy contactBy) {
        //uses derived query to get results.
        List<DTOClient> resultClient = new ArrayList<>();
        clientRepository.findByContactBy(contactBy).forEach(client -> {
            //convert entity to a DTO object.
            DTOClient trans = new DTOClient();
            BeanUtils.copyProperties(client, trans);
            resultClient.add(trans);
        });

        return resultClient;
    }

}
