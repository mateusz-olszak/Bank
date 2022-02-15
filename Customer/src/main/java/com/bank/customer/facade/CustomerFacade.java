package com.bank.customer.facade;

import com.bank.customer.domain.Customer;
import com.bank.customer.dto.*;
import com.bank.customer.mapper.CustomerMapper;
import com.bank.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerFacade {

    private final CustomerService customerService;
    private final CustomerMapper customerMapper;

    @Autowired
    public CustomerFacade(CustomerService customerService, CustomerMapper customerMapper) {
        this.customerService = customerService;
        this.customerMapper = customerMapper;
    }

    public CustomerSearchDto searchCustomer(CustomerDto customerDto) {
        List<CustomerDto> customerDtos = customerMapper.mapToCustomerDtoList(customerService.searchCustomer(customerDto.getPesel()));
        return new CustomerSearchDto(customerDtos);
    }

    public CustomerIdDto createCustomer(CustomerDto customerDto) {
        Customer customer = customerMapper.mapToCustomer(customerDto);
        return customerMapper.mapToCustomerIdDto(customerService.createCustomer(customer));
    }

    public CustomerFilteredDto getCustomers(CustomerFilteredIdDto customersIds) {
        List<CustomerDto> customerDtos = customerMapper.mapToCustomerDtoList(customerService.getCustomers(customersIds.getCustomersIds()));
        return new CustomerFilteredDto(customerDtos);
    }
}
