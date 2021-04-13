package com.microserviceproject.services.quote.domain;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
public class Quote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID;

    private LocalDate quoteDate;
    private String customerName;
    private Double totalPrice;
    private Double totalTax;
    private String comments;
 /*   @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "quote_products",
            joinColumns = @JoinColumn(name = "quote_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    public List<Product> products;

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
*/
    public LocalDate getQuoteDate() {
        return quoteDate;
    }

    public void setQuoteDate(LocalDate quoteDate) {
        this.quoteDate = quoteDate;
    }

    public String getCustomerName() {
        return this.customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
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

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Quote() {
    }

    public Quote(Integer ID, LocalDate quoteDate, String customerName, Double totalPrice, Double totalTax, String comments) {
        this.ID = ID;
        this.quoteDate = quoteDate;
        this.customerName = customerName;
        this.totalPrice = totalPrice;
        this.totalTax = totalTax;
        this.comments = comments;
    }
}
