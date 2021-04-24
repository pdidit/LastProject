package com.microserviceproject.services.client.repo;

import com.microserviceproject.services.client.domian.Client;
import com.microserviceproject.services.client.domain.ContactBy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Integer> {

    //derived qurey for get client with a given first name and surname
    List<Client> findByFirstNameAndSurname(String firstName, String surname);

    //derived query to get a list of the various contact by possible.
    List<Client> findByContactBy(ContactBy contactBy);
}
