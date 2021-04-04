package com.microserviceproject.services.client.repo;

import com.microserviceproject.services.client.domian.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Integer> {
}
