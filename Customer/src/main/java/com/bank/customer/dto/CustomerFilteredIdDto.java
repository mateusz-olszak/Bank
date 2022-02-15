package com.bank.customer.dto;

import java.util.List;

public class CustomerFilteredIdDto {

    private List<Integer> customersIds;

    public CustomerFilteredIdDto() {
    }

    public CustomerFilteredIdDto(List<Integer> customersIds) {
        this.customersIds = customersIds;
    }

    public List<Integer> getCustomersIds() {
        return customersIds;
    }
}
