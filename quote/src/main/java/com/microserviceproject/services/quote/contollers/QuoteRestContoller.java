package com.microserviceproject.services.quote.contollers;

import com.microserviceproject.services.client.domian.Client;
import com.microserviceproject.services.quote.domain.Quote;
import com.microserviceproject.services.quote.feign.clients.ClientFeignClient;
import com.microserviceproject.services.quote.service.QuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class QuoteRestContoller {

    @Autowired
    public ClientFeignClient clientFeignClient;

    @Autowired
    private QuoteService quoteService;


   @GetMapping(value = "/client/{id}")
   public Client getClient(@PathVariable Integer id) {
        return clientFeignClient.getClient(id);
   }

   @GetMapping(value="/client/")
   public List<Client> getClients() {
       return clientFeignClient.getClients();
   }

    @GetMapping(value = "/quote")
    public List<Quote> getQuotes() {
        return quoteService.getQuotes();
    }
}
