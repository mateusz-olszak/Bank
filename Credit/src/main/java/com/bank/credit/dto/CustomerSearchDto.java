package com.bank.credit.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerSearchDto {
    @JsonProperty("searchResult")
    private CustomerClientDto[] searchResult;

    public CustomerSearchDto() {
    }

    public CustomerSearchDto(CustomerClientDto[] searchResult) {
        this.searchResult = searchResult;
    }

    public CustomerClientDto[] getSearchResult() {
        return searchResult;
    }
}
