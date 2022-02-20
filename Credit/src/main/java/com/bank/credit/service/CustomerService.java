package com.bank.credit.service;

import com.bank.credit.client.CustomerClient;
import com.bank.credit.dto.CustomerClientDto;
import com.bank.credit.dto.CustomerDto;
import com.bank.credit.dto.CustomerIdDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerClient customerClient;

    @Autowired
    public CustomerService(CustomerClient customerClient) {
        this.customerClient = customerClient;
    }

    public CustomerIdDto createCustomer(CustomerDto customerDto) {
        return customerClient.createCustomer(customerDto).get();
    }

    public Optional<CustomerClientDto> searchCustomer(CustomerDto customerDto) {
        List<CustomerClientDto> customerDtos = customerClient.searchCustomer(customerDto);
        return customerDtos.size() > 0 ? Optional.ofNullable(customerDtos.get(0)) : Optional.ofNullable(customerDtos.get(0));
    }

    public List<CustomerClientDto> getCustomers(List<Integer> customersIds) {
        return customerClient.filterCustomers(customersIds);
    }
}
