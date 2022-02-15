package com.bank.customer.controller;

import com.bank.customer.dto.*;
import com.bank.customer.facade.CustomerFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class CustomerController {

    private final CustomerFacade customerFacade;

    @Autowired
    public CustomerController(CustomerFacade customerFacade) {
        this.customerFacade = customerFacade;
    }

    @PostMapping("/customer")
    public CustomerIdDto createCustomer(@RequestBody CustomerDto customerDto) {
        return customerFacade.createCustomer(customerDto);
    }

    @PostMapping("/customer/search")
    public CustomerSearchDto searchCustomer(@RequestBody CustomerDto customerDto) {
        return customerFacade.searchCustomer(customerDto);
    }

    @PostMapping("/customer/filtered")
    public CustomerFilteredDto getCustomers(@RequestBody CustomerFilteredIdDto customersIds) {
        return customerFacade.getCustomers(customersIds);
    }
}
