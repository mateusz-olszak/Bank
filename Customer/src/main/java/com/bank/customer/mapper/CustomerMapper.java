package com.bank.customer.mapper;

import com.bank.customer.domain.Customer;
import com.bank.customer.dto.CustomerDto;
import com.bank.customer.dto.CustomerIdDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerMapper {

    public Customer mapToCustomer(CustomerDto customerDto) {
        return new Customer(
                customerDto.getFirstName(),
                customerDto.getLastName(),
                customerDto.getPesel()
        );
    }

    public CustomerDto mapToCustomerDto(Customer customer) {
        return new CustomerDto(
                customer.getId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getPesel()
        );
    }

    public CustomerIdDto mapToCustomerIdDto(Customer customer) {
        return new CustomerIdDto(
                customer.getId()
        );
    }

    public List<CustomerDto> mapToCustomerDtoList(List<Customer> customers) {
        return customers.stream()
                .map(this::mapToCustomerDto)
                .collect(Collectors.toList());
    }
}
