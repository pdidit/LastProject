package com.microserviceproject.services.quote.dto;

import com.microserviceproject.services.client.dto.DTOClient;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Range;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * DTO object intended to be used to call outside the application
 * It will include the client info in the quote.
 */
@ApiModel(description = "DTO for full details of the quotes")
public class DTOAllQuote {

    @ApiModelProperty(notes = "ID of the Quote")
    private Integer ID;

    @ApiModelProperty(notes = "Customer Info of the quote")
    @NotNull(message = "Customer need to create a quote")
    private DTOClient customer;

    @ApiModelProperty(notes = "Date of the Quote")
    @NotNull(message = "Date of quote needed")
    private LocalDate quoteDate;

    @ApiModelProperty(notes = "Total price of the Quote")
    @NotNull(message = "Price of Quote need to be maintained.")
    @Range(min = 0, message = "Price must be more than 0")
    private Double totalPrice;

    @ApiModelProperty(notes = "Total tax of the Quote")
    @NotNull(message ="Tax amount must be maintained")
    @Range(min = 0, message ="Tax amount must be above 0" )
    private Double totalTax;

    @ApiModelProperty(notes = "Contains the details of the Quote")
    @NotNull(message = "Quote must contains some detail of the quote")
    private String detailsQuote;

    //Getters AND Setters for the class.

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public DTOClient getCustomer() {
        return customer;
    }

    public void setCustomer(DTOClient customer) {
        this.customer = customer;
    }

    public LocalDate getQuoteDate() {
        return quoteDate;
    }

    public void setQuoteDate(LocalDate quoteDate) {
        this.quoteDate = quoteDate;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Double getTotalTax() {
        return this.totalTax;
    }

    public void setTotalTax(Double totalTax) {
        this.totalTax = totalTax;
    }

    public String getDetailsQuote() {
        return detailsQuote;
    }

    public void setDetailsQuote(String detailsQuote) {
        this.detailsQuote = detailsQuote;
    }

    //Constructors for the class.
    public DTOAllQuote(Integer ID, DTOClient customer, LocalDate quoteDate, Double totalPrice, Double totalTax, String detailsQuote) {
        this.ID = ID;
        this.customer = customer;
        this.quoteDate = quoteDate;
        this.totalPrice = totalPrice;
        this.totalTax = totalTax;
        this.detailsQuote = detailsQuote;
    }

    public DTOAllQuote() {
    }
}
