package com.microserviceproject.services.quote.feign.clients;

import com.microserviceproject.services.client.domian.Client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient("zuul-api-gateway")
public interface ClientFeignClient {

    @GetMapping(value = "client-service/client")
    List<Client> getClients();

    @GetMapping(value = "client-service/client/{id}")
    Client getClient(@PathVariable Integer id);

    @DeleteMapping(value = "client-service/client/{id}")
    void deleteClient(@PathVariable Integer id);

    @PutMapping(value = "client-service/client/{id}")
    Client updateClient(@PathVariable Integer id, @RequestBody Client Client);

    @PostMapping(value = "client-service/client")
    Client createClient(@RequestBody Client Client);

}
