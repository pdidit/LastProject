package com.microserviceproject.services.quote.service;

import com.microserviceproject.services.quote.domain.Quote;
import com.microserviceproject.services.quote.repos.QuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuoteService {

    @Autowired
    private QuoteRepository quoteRepository;

    public List<Quote> getQuotes() {
        return quoteRepository.findAll();
    }
}
