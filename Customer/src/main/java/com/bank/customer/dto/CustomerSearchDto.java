package com.bank.customer.dto;

import java.util.List;

public class CustomerSearchDto {

    private List<CustomerDto> searchResult;

    public CustomerSearchDto(List<CustomerDto> searchResult) {
        this.searchResult = searchResult;
    }

    public List<CustomerDto> getSearchResult() {
        return searchResult;
    }
}
