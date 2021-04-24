package com.microserviceproject.services.quote.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Range;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * DTO object to be used to call into the Quote service
 * The difference in that I am using a Integer for the client ID
 * instead of a DTOClient object
 */
@ApiModel(description = "DTO for moving the Quote info.")
public class DTOQuote {

    @ApiModelProperty(notes = "ID of the Quote")
    private Integer ID;

    @ApiModelProperty(notes = "Customer Info of the quote")
    @NotNull(message = "Customer need to create a quote")
    private Integer customerID;

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

    public Integer getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Integer customerID) {
        this.customerID = customerID;
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
        return totalTax;
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
    public DTOQuote(Integer ID, Integer customerID, LocalDate quoteDate, Double totalPrice, Double totalTax, String detailsQuote) {
        this.ID = ID;
        this.customerID = customerID;
        this.quoteDate = quoteDate;
        this.totalPrice = totalPrice;
        this.totalTax = totalTax;
        this.detailsQuote = detailsQuote;
    }

    public DTOQuote() {
    }
}
