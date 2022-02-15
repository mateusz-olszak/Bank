package com.bank.customer.dto;

import java.util.List;

public class CustomerFilteredDto {

    private List<CustomerDto> customers;

    public CustomerFilteredDto(List<CustomerDto> customerDtoList) {
        this.customers = customerDtoList;
    }

    public List<CustomerDto> getCustomers() {
        return customers;
    }
}
