package com.bank.customer.unit.mapper;

import com.bank.customer.domain.Customer;
import com.bank.customer.dto.CustomerDto;
import com.bank.customer.dto.CustomerIdDto;
import com.bank.customer.mapper.CustomerMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CustomerMapperTestSuite {

    @Autowired
    private CustomerMapper customerMapper;

    @Test
    void test_mapToCustomerDto() {
        // Given
        Customer customer = new Customer("Jan", "Kowalski", "85123456789");
        // When
        CustomerDto resultCustomerDto = customerMapper.mapToCustomerDto(customer);
        // Then
        assertInstanceOf(CustomerDto.class, resultCustomerDto);
        assertEquals("Jan", resultCustomerDto.getFirstName());
        assertEquals("Kowalski", resultCustomerDto.getLastName());
    }

    @Test
    void test_mapToCustomer() {
        // Given
        CustomerDto customerDto = new CustomerDto("Jan","Kowalski","85123456789");
        // When
        Customer resultCustomer = customerMapper.mapToCustomer(customerDto);
        // Then
        assertInstanceOf(Customer.class, resultCustomer);
        assertEquals("Jan", resultCustomer.getFirstName());
        assertEquals("Kowalski", resultCustomer.getLastName());
    }

    @Test
    void test_mapToCustomerIdDto() {
        // Given
        Customer customer = new Customer(1,"Jan", "Kowalski", "85123456789");
        // When
        CustomerIdDto resultCustomerIdDto = customerMapper.mapToCustomerIdDto(customer);
        // Then
        assertInstanceOf(CustomerIdDto.class, resultCustomerIdDto);
        assertEquals(1, resultCustomerIdDto.getCustomerId());
    }

    @Test
    void test_mapToCustomerDtoList() {
        // Given
        Customer customer1 = new Customer("Jan", "Kowalski", "85123456789");
        Customer customer2 = new Customer("Jan", "Kowalski", "85123456789");
        Customer customer3 = new Customer("Jan", "Kowalski", "85123456789");
        List<Customer> customers = List.of(customer1, customer2, customer3);
        // When
        List<CustomerDto> resultCustomersListDto = customerMapper.mapToCustomerDtoList(customers);
        // Then
        assertInstanceOf(CustomerDto.class, resultCustomersListDto.get(0));
        assertEquals(3, resultCustomersListDto.size());
    }
}
