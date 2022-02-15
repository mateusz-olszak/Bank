package com.bank.credit.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerClientDto {

    @JsonProperty("customerId")
    private int customerId;
    @JsonProperty("firstName")
    private String firstName;
    @JsonProperty("lastName")
    private String lastName;
    @JsonProperty("pesel")
    private String pesel;

    public CustomerClientDto() {
    }

    public CustomerClientDto(int customerId , String firstName, String lastName, String pesel) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.pesel = pesel;
    }

    public int getCustomerId() {
        return customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPesel() {
        return pesel;
    }
}
