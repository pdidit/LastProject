package com.microserviceproject.client.clientapplication.controller;

import com.microserviceproject.client.clientapplication.exception.ExistingQuotesException;
import com.microserviceproject.client.clientapplication.feignClient.QuoteFeignClient;
import com.microserviceproject.services.client.dto.DTOClient;
import com.microserviceproject.services.quote.dto.DTOAllQuote;
import com.microserviceproject.services.quote.dto.DTOQuote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.*;

/**
 * This is the controller class for the client applications.
 * It will look after all the communication to the difference services.
 */
@RestController
public class ApplicationController {

    @Autowired
    public QuoteFeignClient quoteFeignClient;

    /**
     * Will return the environment variables for the client application.
     * @return String Environment variables for Client application.
     */
    @GetMapping("/client/variable")
    public String clientVariable() {
        return quoteFeignClient.getCLientPathVariable();
    }

    /**
     * Returns the list of the Clients in the application.
     * @return Lis<Quote> Returns a list of the clients
     */
    @GetMapping(value = "/client")
    public List<DTOClient> getClientList() { return quoteFeignClient.getClientList(); }

    /**
     * Returns the Client information for a specfic client
     * @param id The ID of the customer that wish to retrieve.
     * @return DTOClient Client info that for the id provided.
     */
    @GetMapping(value = "/client/{id}")
    public DTOClient getClientInfo(@PathVariable Integer id) { return quoteFeignClient.getClientInfo(id); }

    /**
     * Deletes the Client in question
     * As Long as there is no quotes associated to that client
     * @param id Integer The ID that is being deleted.
     */
    @DeleteMapping(value = "/client/{id}")
    void deleteClientInfo(@PathVariable Integer id) {
        List<DTOQuote> existingQuotes = quoteFeignClient.searchQuotesForCustomer(id);
        if (existingQuotes.isEmpty()) {
            quoteFeignClient.deleteClientInfo(id);
        } else {
            throw new ExistingQuotesException("Quotes exist for Customer " + id + " !");
        }
    }

    /**
     * Update the Client information.
     * @param id Integer The ID of the user that is being updated.
     * @param client DTOClient The Body of the client that is being updated.
     * @return DTOClient The Client info that was updated.
     */
    @PutMapping(value = "/client/{id}")
    public DTOClient updateClientInfo(@PathVariable Integer id, @RequestBody @Valid DTOClient client) {
        return quoteFeignClient.updateClientInfo(id, client);
    }

    /**
     * Create a new Client into the system
     * @param client Provided the client information to created.
     * @return ResponseEntity Return the Client information that was created.
     */
    @PostMapping(value = "/client")
    public ResponseEntity createClientInfo(@RequestBody @Valid DTOClient client) {
        DTOClient newClient = quoteFeignClient.createClientInfo(client);

        //Give response entity with URI of new Client.
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(newClient.getID()).toUri();
        return ResponseEntity.created(location).build();
    }

    /**
     * Return the environment Variables of the Quotes service
     * @return String Returns the Environment variables for qoute service
     */
    @GetMapping("/quote/variable")
    public String quoteVariable() {
        return quoteFeignClient.getPathVariable();
    }

    /**
     * Returns a list of all the Quotes in the system
     * @return List<DTOALLQuote> A List of the Quotes that exist in the system.
     */
    @GetMapping(value = "/quote")
    public List<DTOAllQuote> getQuoteList() {
        //create a list of DTOAllQuote
        List<DTOAllQuote> returnQuotes = new ArrayList<>();
        //make call for the list of th quotes in the system.
        quoteFeignClient.getQuoteList().forEach(quote -> {
            DTOAllQuote returnQuote = new DTOAllQuote();
            //get the customer information from the client service.
            returnQuote.setCustomer(quoteFeignClient.getClientInfo(quote.getCustomerID()));
            //covert the DTOQuote into the DTOAllQuote list.
            returnQuote.setID(quote.getID());
            returnQuote.setQuoteDate(quote.getQuoteDate());
            returnQuote.setDetailsQuote(quote.getDetailsQuote());
            returnQuote.setTotalPrice(quote.getTotalPrice());
            returnQuote.setTotalTax(quote.getTotalTax());
            returnQuotes.add(returnQuote);
        });

        return returnQuotes; }

    /**
     * Retieves the Quote that is requested in the ID
     * @param id The ID of the Quote retrieved
     * @return DTOALLQuote returns the Quote information
     */
    @GetMapping(value = "/quote/{id}")
    public DTOAllQuote getQuote(@PathVariable Integer id) {
        //create a DTOAllQuote object to be returned
        DTOAllQuote returnQuote = new DTOAllQuote();

        //Call for the Quote information.
        //Convert to DTOAllQuote object.
        DTOQuote quote = quoteFeignClient.getQuote(id);
        returnQuote.setCustomer(quoteFeignClient.getClientInfo(quote.getCustomerID()));
        returnQuote.setID(quote.getID());
        returnQuote.setQuoteDate(quote.getQuoteDate());
        returnQuote.setDetailsQuote(quote.getDetailsQuote());
        returnQuote.setTotalPrice(quote.getTotalPrice());
        returnQuote.setTotalTax(quote.getTotalTax());
        return returnQuote;
    }

    /**
     * Delete a quote that exist in the system
     * @param id Integer The ID that is going to deleted.
     */
    @DeleteMapping(value = "/quote/{id}")
    public void deleteQuote(@PathVariable Integer id) {
        quoteFeignClient.deleteQuote(id);
    }

    @PutMapping(value = "/quote/{id}")
    public ResponseEntity updateQuote(@PathVariable Integer id, @RequestBody @Valid DTOAllQuote quote) {

        //Checks that the customer is in the system.
        DTOClient client = quoteFeignClient.getClientInfo(quote.getCustomer().getID());
        if(client == null){
            throw new RuntimeException("Client doesn't exist");
        }

        //Give response entity with URI of update Quote.
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(client.getID()).toUri();
        return ResponseEntity.created(location).build();
    }

    /**
     * Create a new Quote into the system
     * @param quote DTOAllQuote the quote information for quote created
     * @return ResponseEntity Response of the with link to newly created
     */
    @PostMapping(value = "/quote")
    public ResponseEntity createQuote(@RequestBody @Valid DTOAllQuote quote) {
        //Checks that the customer is in the system.
        DTOClient client = quoteFeignClient.getClientInfo(quote.getCustomer().getID());
        if(client == null){
            throw new RuntimeException("Client doesn't exist");
        }

        //Convert to DTOQuote so it can be saved in the Quote service.
        DTOQuote newQuote = new DTOQuote();
        newQuote.setCustomerID(quote.getCustomer().getID());
        newQuote.setQuoteDate(quote.getQuoteDate());
        newQuote.setDetailsQuote(quote.getDetailsQuote());
        newQuote.setTotalPrice(quote.getTotalPrice());
        newQuote.setTotalTax(quote.getTotalTax());
        DTOQuote savedQuote = quoteFeignClient.createQuote(newQuote);

        //Give response entity with URI of new Quote.
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedQuote.getID()).toUri();
        return ResponseEntity.created(location).build();
    }
}
