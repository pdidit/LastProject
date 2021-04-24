package com.microserviceproject.services.quote.contollers;

import com.microserviceproject.services.client.domain.ContactBy;
import com.microserviceproject.services.client.dto.DTOClient;
import com.microserviceproject.services.quote.feign.clients.ClientFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

/**
 * Rest Controller which will call over the feign client.
 */
@RestController
public class ClientRestController {

    @Autowired
    public ClientFeignClient clientFeignClient;

    /**
     * Returns the client path variables
     * @return String A string of the path variables
     */
    @GetMapping(value = "/client-variable")
    public String getCLientPathVariable() { return clientFeignClient.getPathVariable(); }

    /**
     * Returns the list of the clients.
     * @return List<DTOClient> Returns a list of DTOClient
     */
    @GetMapping(value = "/client")
    public List<DTOClient> getClientList() {return clientFeignClient.getClients(); }

    /**
     * Get a Single Client from the client service.
     * @param id Integer ID of the client want to be retrieved
     * @return DTOClient Returns a DTOClient of the client requested.
     */
    @GetMapping(value = "/client/{id}")
    public DTOClient getClientInfo(@PathVariable Integer id) { return clientFeignClient.getClient(id); }

    /**
     * Delete the client in the system
     * @param id Integer ID of the client being deleted.
     */
    @DeleteMapping(value = "/client/{id}")
    public void deleteClientInfo(@PathVariable Integer id) {
        clientFeignClient.deleteClient(id);
    }

    /**
     * End point for updating the client
     * @param id Integer ID that was being updated.
     * @param client DTOClient Body of the client information
     * @return DTOClient returns the Client information.
     */
    @PutMapping(value = "/client/{id}")
    public DTOClient updateClientInfo(@PathVariable Integer id, @RequestBody @Valid DTOClient client) {
        return clientFeignClient.updateClient(id, client);
    }

    /**
     * Endpoint to create a new Client
     * @param client DTOClient Body of the client information to be created
     * @return DTOClient Returns ths client object being created.
     */
    @PostMapping(value = "/client")
    public DTOClient createClientInfo(@RequestBody @Valid DTOClient client) { return clientFeignClient.createClient(client); }

    /**
     * endpoint to search for a client with first name and surname
     * @param firstName String First name to be searched.
     * @param surname String surname to be searched.
     * @return List<DTOClient> Returns a list of the client back from the search.
     */
    @GetMapping(value = "/client/search/names")
    public List<DTOClient> searchFirstNameAndSuranme(@RequestParam String firstName, @RequestParam String surname) {
        return clientFeignClient.getClientSearch(firstName,surname);
    }

    /**
     * End point to search by the different contact type
     * @param contactBy ContactBy Enum parameter for the different contact types.
     * @return List<DTOClient> Returns a list of the client back from the search.
     */
    @GetMapping(value = "/client/search/contactby")
    public List<DTOClient> searchContactBy(@RequestParam ContactBy contactBy) {
        return clientFeignClient.getSearchContactBy(contactBy);
    }
}
