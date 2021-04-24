package com.microserviceproject.client.clientapplication.feignClient;

import com.microserviceproject.services.client.domain.ContactBy;
import com.microserviceproject.services.client.dto.DTOClient;
import com.microserviceproject.services.quote.dto.DTOQuote;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

/**
 * Feign client for all the endpoint in the quote service
 * This will also include the client service
 */
@FeignClient("zuul-api-gateway")
public interface QuoteFeignClient {

    @GetMapping(value = "/quote-service/client-variable")
    String getCLientPathVariable();

    @GetMapping(value = "/quote-service/client")
    List<DTOClient> getClientList();

    @GetMapping(value = "/quote-service/client/{id}")
    DTOClient getClientInfo(@PathVariable Integer id);

    @DeleteMapping(value = "/quote-service/client/{id}")
    void deleteClientInfo(@PathVariable Integer id);

    @PutMapping(value = "/quote-service/client/{id}")
    DTOClient updateClientInfo(@PathVariable Integer id, @RequestBody DTOClient client);

    @PostMapping(value = "/quote-service/client")
    DTOClient createClientInfo(@RequestBody DTOClient client);

    @GetMapping(value = "/quote-service/client/search/names")
    List<DTOClient> searchFirstNameAndSuranme(@RequestParam String firstName, @RequestParam String surname);

    @GetMapping(value = "/quote-service/client/search/contactby")
    List<DTOClient> searchConactBy(@RequestParam ContactBy contactBy);

    @GetMapping(value = "/quote-service/pathvariable")
    String getPathVariable();

    @GetMapping(value = "/quote-service/quote")
    List<DTOQuote> getQuoteList();

    @GetMapping(value = "/quote-service/quote/{id}")
    DTOQuote getQuote(@PathVariable Integer id);

    @DeleteMapping(value = "/quote-service/quote/{id}")
    void deleteQuote(@PathVariable Integer id);

    @PutMapping(value = "/quote-service/quote/{id}")
    DTOQuote updateQuote(@PathVariable Integer id, @RequestBody @Valid DTOQuote quote);

    @PostMapping(value = "/quote-service/quote")
    DTOQuote createQuote(@RequestBody @Valid DTOQuote quote);

    @GetMapping(value = "/quote-service/quote/search/price/")
    List<DTOQuote> searchPriceOver(@RequestParam Double TotalPrice);

    @GetMapping(value = "/quote-service/quote/search/cust/")
    List<DTOQuote> searchQuotesForCustomer(@RequestParam Integer id);
}
