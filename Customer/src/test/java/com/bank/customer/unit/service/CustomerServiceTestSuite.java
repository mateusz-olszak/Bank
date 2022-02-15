package com.bank.customer.unit.service;

import com.bank.customer.dao.CustomerRepository;
import com.bank.customer.domain.Customer;
import com.bank.customer.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTestSuite {

    @InjectMocks
    private CustomerService customerService;

    @Mock
    private CustomerRepository customerRepository;

    @Test
    void test_createCustomer() {
        // Given
        String pesel = "85123456789";
        Customer customer = new Customer("Jan","Kowalski",pesel);
        when(customerRepository.save(customer)).thenReturn(customer);
        // When
        Customer savedCustomer = customerService.createCustomer(customer);
        // Then
        assertNotNull(savedCustomer);
        assertEquals(pesel,savedCustomer.getPesel());
        verify(customerRepository, times(1)).save(customer);
    }

    @Test
    void test_searchCustomer() {
        // Given
        String pesel = "85123456789";
        Customer customer = new Customer("Jan","Kowalski",pesel);
        List<Customer> customers = List.of(customer);
        when(customerRepository.findAllByPesel(pesel)).thenReturn(customers);
        // When
        List<Customer> resultList = customerService.searchCustomer(pesel);
        // Then
        assertEquals(1, resultList.size());
        assertEquals(pesel, resultList.get(0).getPesel());
        verify(customerRepository, times(1)).findAllByPesel(pesel);
    }

    @Test
    void test_getCustomers() {
        // Given
        List<Integer> customersIds = List.of(1,2,3);
        Customer customer1 = new Customer("Jan","Kowalski","85123456789");
        Customer customer2 = new Customer("Jan","Kowalski","96453627182");
        Customer customer3 = new Customer("Jan","Kowalski","00051234321");
        List<Customer> customers = List.of(customer1, customer2, customer3);
        when(customerRepository.findAllByIdIn(customersIds)).thenReturn(customers);
        // When
        List<Customer> resultList = customerService.getCustomers(customersIds);
        // Then
        assertEquals(3, resultList.size());
        verify(customerRepository, times(1)).findAllByIdIn(customersIds);
    }
}
