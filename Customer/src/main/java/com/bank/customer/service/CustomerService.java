package com.bank.customer.service;

import com.bank.customer.dao.CustomerRepository;
import com.bank.customer.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> searchCustomer(String pesel) {
        return customerRepository.findAllByPesel(pesel);
    }

    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public List<Customer> getCustomers(List<Integer> customersId) {
        return customerRepository.findAllByIdIn(customersId);
    }
}
