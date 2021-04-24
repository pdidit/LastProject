package com.microserviceproject.services.quote.contollers;

import com.microserviceproject.services.quote.domain.Quote;
import com.microserviceproject.services.quote.dto.DTOQuote;
import com.microserviceproject.services.quote.exception.NoQuoteExistException;
import com.microserviceproject.services.quote.repos.QuoteRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Rest Controller for the Quote controller
 */
@RestController
public class QuoteRestContoller {

    @Autowired
    private QuoteRepository quoteRepository;

    @Value("${quote-service.profile-variable1}")
    private String profileVariable1;

    @Value("${quote-service.profile-variable2}")
    private String profileVariable2;

    /**
     * Returns the path variables from the quote service
     * @return String Returns the path variables from the quote service
     */
    @GetMapping(value = "/pathvariable")
    public String getPathVariable() {
        return "Profile parameter 1: " + profileVariable1 +
                "\nProfile parameter 2: " + profileVariable2 ;
    }

    /**
     * End point that returns a List of quotes
     * @return List<DTOQuote> Returns a List of the Quotes
     */
    @GetMapping(value = "/quote")
    public List<DTOQuote> getQuoteList() {
        //calls for all the quotes in system
        List<Quote> allQuotes = quoteRepository.findAll();

        //convert the quotes into a DTOQuotes
        List<DTOQuote> allTransQuote = new ArrayList<>();
        allQuotes.forEach(quote -> {
            DTOQuote trans = new DTOQuote();
            BeanUtils.copyProperties(quote, trans);
            allTransQuote.add(trans);
        });
        //return the list of the quotes.
        return allTransQuote;
    }

    /**
     * Get a single Quote from the system
     * @param id Integer ID of the Quote being retrieved.
     * @return DTOQuote Returns the Quote from the system
     */
    @GetMapping(value = "/quote/{id}")
    public DTOQuote getQuote(@PathVariable Integer id) {
        //returns the DTOQuote or else throws exception.
        return quoteRepository.findById(id).map(quote -> {
            DTOQuote outQuote = new DTOQuote();
            //copies the quote to a DTOQuote
            BeanUtils.copyProperties(quote, outQuote );
            return outQuote;
        }).orElseThrow(() -> new NoQuoteExistException("Client doesn't exist"));
    }

    /**
     * End point to delete the quote from the system
     * @param id Integer ID of the quote to deleted.
     */
    @DeleteMapping(value = "/quote/{id}")
    public void deleteQuote(@PathVariable Integer id) {
        //check that quote exist or throw exception
        Optional<Quote> delQuote = quoteRepository.findById(id);
        if (delQuote.isPresent()) {
            quoteRepository.deleteById(delQuote.get().getID());
        } else {
            throw new NoQuoteExistException("Quote " + id + " doesn't exist");
        }
    }

    /**
     * End point to update the quotes.
     * @param id Integer ID of the quote that is being updated.
     * @param quote DTOQuote Body of the quote being created.
     * @return DTOQuote Returns the quote being updated.
     */
    @PutMapping(value = "/quote/{id}")
    public DTOQuote updateQuote(@PathVariable Integer id, @RequestBody @Valid DTOQuote quote) {
        //check that quote ID exist
        Quote updateQuote = quoteRepository.findById(id)
                //if exist update the quotes
                .map(oldQuote -> {
                    BeanUtils.copyProperties(quote, oldQuote);
                    oldQuote.setID(id);
                    return quoteRepository.saveAndFlush(oldQuote);
                })
                //if not crate a new quote.
                .orElseGet(() -> {
                    Quote saveQuote = new Quote();
                    BeanUtils.copyProperties(quote, saveQuote);
                    return quoteRepository.saveAndFlush(saveQuote);
                });

        //convert to a DTO
        DTOQuote returnQuote = new DTOQuote();
        BeanUtils.copyProperties(updateQuote,returnQuote);

        //return DTO object
        return returnQuote;
    }

    /**
     * End point to creat a new Quote
     * @param quote DTOQuote Body of the quote being created.
     * @return DTOQuote Returns the newly created quote.
     */
    @PostMapping(value = "/quote")
    public DTOQuote createQuote(@RequestBody @Valid DTOQuote quote) {
        //Copy DTO object to entity
        Quote savingQuote = new Quote();
        BeanUtils.copyProperties(quote, savingQuote );
        Quote newQuote = quoteRepository.saveAndFlush(savingQuote);

        //convert to entity object to a DTO
        DTOQuote returnQuote = new DTOQuote();
        BeanUtils.copyProperties(newQuote, returnQuote );

        //returns the DTO object.
        return returnQuote;
    }

    /**
     * End point to search for quotes above a certain amount.
     * @param TotalPrice Double Price above which I want to search for.
     * @return List<DTOQuote> Returns the list of quotes over a price.
     */
    @GetMapping(value = "/quote/search/price/")
    public List<DTOQuote> searchPriceOver(@RequestParam Double TotalPrice){
        //use derived query an converts into a DTOQuote
        List<DTOQuote> allTransQuote = new ArrayList<>();
        quoteRepository.findByTotalPriceGreaterThanEqual(TotalPrice).forEach(quote -> {
            DTOQuote trans = new DTOQuote();
            BeanUtils.copyProperties(quote, trans);
            allTransQuote.add(trans);
        });
        //return the list returned from query
        return allTransQuote;
    }

    /**
     * End point to search for quotes with customer ID
     * @param id Integer ID of the customer ID that you want the quotes related to it
     * @return List<DTOQuote> Returns the list of quotes for that customer.
     */
    @GetMapping(value = "/quote/search/cust/")
    public List<DTOQuote> searchQuotesForCustomer(@RequestParam Integer id){
        //use derived query an converts into a DTOQuote
        List<DTOQuote> allTransQuote = new ArrayList<>();
        quoteRepository.findByCustomerID(id).forEach(quote -> {
            DTOQuote trans = new DTOQuote();
            BeanUtils.copyProperties(quote, trans);
            allTransQuote.add(trans);
        });
        //return the list returned from query
        return allTransQuote;
    }
}
