package com.bank.credit.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerIdDto {

    @JsonProperty("customerId")
    private int customerId;

    public CustomerIdDto() {
    }

    public CustomerIdDto(int customerId) {
        this.customerId = customerId;
    }

    public int getCustomerId() {
        return customerId;
    }
}
