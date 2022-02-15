package com.bank.credit.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerFilteredDto {

    @JsonProperty("customers")
    private CustomerClientDto[] customers;

    public CustomerFilteredDto() {
    }

    public CustomerFilteredDto(CustomerClientDto[] customers) {
        this.customers = customers;
    }

    public CustomerClientDto[] getCustomers() {
        return customers;
    }
}
