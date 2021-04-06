package com.microserviceproject.services.quote.contollers;

import com.microserviceproject.services.client.domian.Client;
import com.microserviceproject.services.quote.domain.Quote;
import com.microserviceproject.services.quote.exception.NoQuoteExistException;
import com.microserviceproject.services.quote.feign.clients.ClientFeignClient;
import com.microserviceproject.services.quote.repos.QuoteRepository;
import com.microserviceproject.services.quote.service.QuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class QuoteRestContoller {

    @Autowired
    public ClientFeignClient clientFeignClient;

    @Autowired
    private QuoteRepository quoteRepository;

    @Value("${quote-service.profile-variable}")
    private String profileVariable;

    @GetMapping(value = "/client-variable")
    public String getCLientPathVariable() { return clientFeignClient.getPathVariable(); }

    @GetMapping(value = "/client")
    public List<Client> getClientList() {return clientFeignClient.getClients(); }

    @GetMapping(value = "/client/{id}")
    public Client getClientInfo(@PathVariable Integer id) { return clientFeignClient.getClient(id); }

    @DeleteMapping(value = "/client/{id}")
    public void deleteClientInfo(@PathVariable Integer id) {
        clientFeignClient.deleteClient(id);
    }

    @PutMapping(value = "/client/{id}")
    public Client updateClientInfo(@PathVariable Integer id, @RequestBody Client client) {return clientFeignClient.updateClient(id, client); }

    @PostMapping(value = "/client")
    public Client createClientInfo(@RequestBody Client client) { return clientFeignClient.createClient(client); }

    @GetMapping(value = "/pathvariabele")
    public String getPathVariable() {
        return profileVariable;
    }

    @GetMapping(value = "/quote")
    public List<Quote> getQuoteList() { return quoteRepository.findAll(); }

    @GetMapping(value = "/quote/{id}")
    public Quote getQuote(@PathVariable Integer id) {
        Quote qryQuote = quoteRepository.getOne(id);
        if (qryQuote != null) {
            return qryQuote;
        } else {
            throw new NoQuoteExistException("Client doesn't exist");
        }
    }

    @DeleteMapping(value = "/quote/{id}")
    public void deleteQuote(@PathVariable Integer id) {
        Quote qryQuote = quoteRepository.getOne(id);
        if (qryQuote != null) {
            quoteRepository.deleteById(qryQuote.getID());
        } else {
            throw new NoQuoteExistException("Client doesn't exist");
        }
    }

    @PutMapping(value = "/quote/{id}")
    public Quote updateQuote(@PathVariable Integer id, @RequestBody @Validated Quote quote) {
        return quoteRepository.findById(id)
                .map(oldQuote -> {
                    return quoteRepository.save(oldQuote);
                })
                .orElseGet(() -> {
                    return quoteRepository.save(quote);
                });
    }

    @PostMapping(value = "/quote")
    public Quote createQuote(@RequestBody @Validated Quote quote) { return quoteRepository.saveAndFlush(quote); }
}
