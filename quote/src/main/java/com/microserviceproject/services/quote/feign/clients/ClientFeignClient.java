package com.microserviceproject.services.quote.feign.clients;

import com.microserviceproject.services.client.domain.ContactBy;
import com.microserviceproject.services.client.dto.DTOClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

/**
 * Feign client for client service.
 */
@FeignClient("zuul-api-gateway")
public interface ClientFeignClient {

    @GetMapping(value = "client-service/pathvariabele")
    String getPathVariable();

    @GetMapping(value = "client-service/client")
    List<DTOClient> getClients();

    @GetMapping(value = "client-service/client/{id}")
    DTOClient getClient(@PathVariable Integer id);

    @DeleteMapping(value = "client-service/client/{id}")
    void deleteClient(@PathVariable Integer id);

    @PutMapping(value = "client-service/client/{id}")
    DTOClient updateClient(@PathVariable Integer id, @RequestBody @Valid DTOClient client);

    @PostMapping(value = "client-service/client")
    DTOClient createClient(@RequestBody @Valid DTOClient client);

    @GetMapping(value = "client-service/client/search/name")
    List<DTOClient> getClientSearch(@RequestParam String firstName, @RequestParam String surname);

    @GetMapping(value = "client-service/client/search/contact")
    List<DTOClient> getSearchContactBy(@RequestParam ContactBy contactBy);

}
