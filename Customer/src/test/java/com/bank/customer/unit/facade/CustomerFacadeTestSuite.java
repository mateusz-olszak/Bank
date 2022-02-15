package com.bank.customer.unit.facade;

import com.bank.customer.domain.Customer;
import com.bank.customer.dto.*;
import com.bank.customer.facade.CustomerFacade;
import com.bank.customer.mapper.CustomerMapper;
import com.bank.customer.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CustomerFacadeTestSuite {

    @InjectMocks
    private CustomerFacade customerFacade;

    @Mock
    private CustomerService customerService;
    @Mock
    private CustomerMapper customerMapper;

    @Test
    void test_createCustomer() {
        // Given
        Customer customer = new Customer("Jan", "Kowalski", "85123456789");
        CustomerDto customerDto = new CustomerDto("Jan", "Kowalski", "85123456789");
        CustomerIdDto customerIdDto = new CustomerIdDto(1);
        when(customerMapper.mapToCustomer(customerDto)).thenReturn(customer);
        when(customerService.createCustomer(customer)).thenReturn(customer);
        when(customerMapper.mapToCustomerIdDto(customer)).thenReturn(customerIdDto);
        // When
        CustomerIdDto resultCustomer = customerFacade.createCustomer(customerDto);
        // Then
        assertEquals(1,resultCustomer.getCustomerId());
        verify(customerMapper, times(1)).mapToCustomer(customerDto);
        verify(customerService, times(1)).createCustomer(customer);
        verify(customerMapper, times(1)).mapToCustomerIdDto(customer);
    }

    @Test
    void test_searchCustomer() {
        // Given
        String pesel = "85123456789";
        Customer customer = new Customer("Jan", "Kowalski", pesel);
        CustomerDto customerDto = new CustomerDto("Jan", "Kowalski", pesel);
        List<Customer> customers = List.of(customer);
        List<CustomerDto> customerDtos = List.of(customerDto);
        when(customerService.searchCustomer(pesel)).thenReturn(customers);
        when(customerMapper.mapToCustomerDtoList(customers)).thenReturn(customerDtos);
        // When
        CustomerSearchDto resultCustomerSearchDto = customerFacade.searchCustomer(customerDto);
        // Then
        assertInstanceOf(CustomerSearchDto.class, resultCustomerSearchDto);
        assertEquals(1, resultCustomerSearchDto.getSearchResult().size());
    }

    @Test
    void test_getCustomers() {
        // Given
        CustomerFilteredIdDto customerFilteredIdDto = new CustomerFilteredIdDto(List.of(1,2,3));
        Customer customer1 = new Customer("Jan", "Kowalski", "85123456789");
        CustomerDto customerDto1 = new CustomerDto("Jan", "Kowalski", "85123456789");
        Customer customer2 = new Customer("Adam", "Joseph", "00123456789");
        CustomerDto customerDto2 = new CustomerDto("Adam", "Joseph", "00123456789");
        Customer customer3 = new Customer("Josh", "Adams", "77123456789");
        CustomerDto customerDto3 = new CustomerDto("Josh", "Adams", "77123456789");
        List<Customer> customers = List.of(customer1, customer2, customer3);
        List<CustomerDto> customerDtos = List.of(customerDto1, customerDto2, customerDto3);
        when(customerService.getCustomers(customerFilteredIdDto.getCustomersIds())).thenReturn(customers);
        when(customerMapper.mapToCustomerDtoList(customers)).thenReturn(customerDtos);
        // When
        CustomerFilteredDto resultList = customerFacade.getCustomers(customerFilteredIdDto);
        // Then
        assertEquals(3, resultList.getCustomers().size());
        verify(customerService, times(1)).getCustomers(customerFilteredIdDto.getCustomersIds());
        verify(customerMapper, times(1)).mapToCustomerDtoList(customers);
    }
}
