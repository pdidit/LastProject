package com.microserviceproject.services.quote.repos;

import com.microserviceproject.services.quote.domain.Quote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuoteRepository extends JpaRepository<Quote, Integer> {

    //derived query to get the quote for a customer
    List<Quote> findByCustomerID(Integer id);

    //dervied query to find quote over a certain amount.
    List<Quote> findByTotalPriceGreaterThanEqual(Double totalPrice);

}
