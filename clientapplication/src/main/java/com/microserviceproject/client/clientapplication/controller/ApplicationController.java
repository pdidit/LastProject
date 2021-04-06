package com.microserviceproject.client.clientapplication.controller;

import com.microserviceproject.client.clientapplication.feignClient.QuoteFeignClient;
import com.microserviceproject.services.client.domian.Client;
import com.microserviceproject.services.quote.domain.Quote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ApplicationController {

    @Autowired
    public QuoteFeignClient quoteFeignClient;

    @GetMapping("/client/variable")
    public String clientVariable() {
        return quoteFeignClient.getCLientPathVariable();
    }

    @GetMapping(value = "/client/client")
    List<Client> getClientList() { return quoteFeignClient.getClientList(); }

    @GetMapping(value = "/client/client/{id}")
    Client getClientInfo(@PathVariable Integer id) { return quoteFeignClient.getClientInfo(id); }

    @DeleteMapping(value = "/client/client/{id}")
    void deleteClientInfo(@PathVariable Integer id) { quoteFeignClient.deleteClientInfo(id); }

    @PutMapping(value = "/client/client/{id}")
    Client updateClientInfo(@PathVariable Integer id, @RequestBody Client client) {
        return quoteFeignClient.updateClientInfo(id, client);
    }

    @PostMapping(value = "/client/client")
    Client createClientInfo(@RequestBody Client client) { return quoteFeignClient.createClientInfo(client); }

    @GetMapping("/quote/variable")
    public String quoteVariable() {
        return quoteFeignClient.getPathVariable();
    }

    @GetMapping(value = "/quote/quote")
    List<Quote> getQuoteList() { return quoteFeignClient.getQuoteList(); }

    @GetMapping(value = "/quote/quote/{id}")
    Quote getQuote(@PathVariable Integer id) { return quoteFeignClient.getQuote(id); }

    @DeleteMapping(value = "/quote/quote/{id}")
    void deleteQuote(@PathVariable Integer id) { quoteFeignClient.deleteQuote(id); }

    @PutMapping(value = "/quote/quote/{id}")
    Quote updateQuote(@PathVariable Integer id, @RequestBody @Validated Quote quote) {
        return quoteFeignClient.updateQuote(id, quote);
    }

    @PostMapping(value = "/quote/quote")
    Quote createQuote(@RequestBody @Validated Quote quote) { return quoteFeignClient.createQuote(quote); }
}
