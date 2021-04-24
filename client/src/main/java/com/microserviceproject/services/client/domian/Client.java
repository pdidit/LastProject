package com.microserviceproject.services.client.domian;

import com.microserviceproject.services.client.domain.ContactBy;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
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

    @NotNull(message = "First name of client need to be maintained.")
    @ApiModelProperty(notes = "First name of the client")
    private String firstName;

    @NotNull(message = "Surname of client need to be maintained.")
    @ApiModelProperty(notes = "Surname of the client")
    private String surname;

    @ApiModelProperty("Phone number of the client")
    private Integer phoneNumber;

    @ApiModelProperty(notes = "Last update date of the client")
    private LocalDate lastUpdated;

    @ApiModelProperty(notes = "Creation date of the client")
    private LocalDate creationDate;

    @ApiModelProperty(notes = "Best form to contact client")
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Contact type must be maintained")
    private ContactBy contactBy;

    @Email(message = "Invalid e-mail format")
    @ApiModelProperty(notes = "E-mail address of the client")
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

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public ContactBy getContactBy() {
        return contactBy;
    }

    public void setContactBy(ContactBy contactBy) {
        this.contactBy = contactBy;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Client(Integer ID, String firstName, String surname, Integer phoneNumber, LocalDate lastUpdated, LocalDate creationDate, ContactBy contactBy, String email) {
        this.ID = ID;
        this.firstName = firstName;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.lastUpdated = lastUpdated;
        this.creationDate = creationDate;
        this.contactBy = contactBy;
        this.email = email;
    }

    public Client() {
    }
}
