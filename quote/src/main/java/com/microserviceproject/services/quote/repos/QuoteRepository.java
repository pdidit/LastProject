package com.microserviceproject.services.quote.repos;

import com.microserviceproject.services.quote.domain.Quote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuoteRepository extends JpaRepository<Quote, Integer> {
}
