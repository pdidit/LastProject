package com.microserviceproject.services.client.domian;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@ApiModel(description = "This is the client service for the microservice project")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "ID of the client")
    private Integer ID;
    @NotNull
    @ApiModelProperty(notes = "The first name of the client")
    private String firstName;
    @ApiModelProperty(notes = "The surname of the client")
    private String surname;
    @ApiModelProperty("this is the phone number of the client")
    private Integer phoneNumber;
    @ApiModelProperty(notes = "Last update date of the client")
    private LocalDate lastUpdated;
    @ApiModelProperty(notes = "Creation date of the client")
    private LocalDate creationDate;

    @NotNull
    @Email
    public String email;

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Integer getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDate getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(LocalDate lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationdate) {
        this.creationDate = creationdate;
    }

    public Client(Integer ID, String firstName, String surname, Integer phoneNumber, LocalDate lastUpdated, LocalDate creationdate) {
        this.ID = ID;
        this.firstName = firstName;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.lastUpdated = lastUpdated;
        this.creationDate = creationdate;
    }

    public Client() {
    }
}
