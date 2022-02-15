package com.bank.credit.service;

import com.bank.credit.client.CustomerClient;
import com.bank.credit.dto.CustomerClientDto;
import com.bank.credit.dto.CustomerDto;
import com.bank.credit.dto.CustomerIdDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerClient customerClient;

    @Autowired
    public CustomerService(CustomerClient customerClient) {
        this.customerClient = customerClient;
    }

    public CustomerIdDto createCustomer(CustomerDto customerDto) {
        return customerClient.createCustomer(customerDto);
    }

    public CustomerClientDto searchCustomer(CustomerDto customerDto) {
        List<CustomerClientDto> customerDtos = customerClient.searchCustomer(customerDto);
        return customerDtos.size() > 0 ? customerDtos.get(0) : null;
    }

    public List<CustomerClientDto> getCustomers(List<Integer> customersIds) {
        return customerClient.filterCustomers(customersIds);
    }
}
