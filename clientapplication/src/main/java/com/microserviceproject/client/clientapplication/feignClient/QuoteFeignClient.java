package com.microserviceproject.client.clientapplication.feignClient;

import com.microserviceproject.services.client.domian.Client;
import com.microserviceproject.services.quote.domain.Quote;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient("zuul-api-gateway")
public interface QuoteFeignClient {

    @GetMapping(value = "/quote-service/client-variable")
    String getCLientPathVariable();

    @GetMapping(value = "/quote-service/client")
    List<Client> getClientList();

    @GetMapping(value = "/quote-service/client/{id}")
    Client getClientInfo(@PathVariable Integer id);

    @DeleteMapping(value = "/quote-service/client/{id}")
    void deleteClientInfo(@PathVariable Integer id);

    @PutMapping(value = "/quote-service/client/{id}")
    Client updateClientInfo(@PathVariable Integer id, @RequestBody Client client);

    @PostMapping(value = "/quote-service/client")
    Client createClientInfo(@RequestBody Client client);

    @GetMapping(value = "/quote-service/pathvariabele")
    String getPathVariable();

    @GetMapping(value = "/quote-service/quote")
    List<Quote> getQuoteList();

    @GetMapping(value = "/quote-service/quote/{id}")
    Quote getQuote(@PathVariable Integer id);

    @DeleteMapping(value = "/quote-service/quote/{id}")
    void deleteQuote(@PathVariable Integer id);

    @PutMapping(value = "/quote-service/quote/{id}")
    Quote updateQuote(@PathVariable Integer id, @RequestBody @Validated Quote quote);

    @PostMapping(value = "/quote-service/quote")
    Quote createQuote(@RequestBody @Validated Quote quote);
}
