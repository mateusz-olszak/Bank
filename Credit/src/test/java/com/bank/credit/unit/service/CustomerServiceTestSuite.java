package com.bank.credit.unit.service;

import com.bank.credit.client.CustomerClient;
import com.bank.credit.dto.CustomerClientDto;
import com.bank.credit.dto.CustomerDto;
import com.bank.credit.dto.CustomerIdDto;
import com.bank.credit.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTestSuite {

    @InjectMocks
    private CustomerService customerService;

    @Mock
    private CustomerClient customerClient;

    @Test
    void test_createCustomer() {
        // Given
        CustomerDto customerDto = new CustomerDto("Jan","Kowalski","85123456789");
        CustomerIdDto customerIdDto = new CustomerIdDto(1);
        when(customerClient.createCustomer(customerDto)).thenReturn(customerIdDto);
        // When
        CustomerIdDto result = customerService.createCustomer(customerDto);
        // Then
        assertEquals(1,result.getCustomerId());
        verify(customerClient, times(1)).createCustomer(customerDto);
    }

    @Test
    void test_searchCustomer() {
        // Given
        CustomerDto customerDto = new CustomerDto("Jan","Kowalski","85123456789");
        CustomerClientDto customerClientDto = new CustomerClientDto(1,"Jan","Kowalski","85123456789");
        when(customerClient.searchCustomer(customerDto)).thenReturn(List.of(customerClientDto));
        // When
        CustomerClientDto result = customerService.searchCustomer(customerDto);
        // Then
        assertEquals(1, result.getCustomerId());
        verify(customerClient, times(1)).searchCustomer(customerDto);
    }

    @Test
    void test_getCustomers() {
        // Given
        CustomerClientDto customerClientDto1 = new CustomerClientDto(1,"Jan","Kowalski","85123456789");
        CustomerClientDto customerClientDto2 = new CustomerClientDto(2,"Adam","Nowak","90123456789");
        when(customerClient.filterCustomers(List.of(1,2))).thenReturn(List.of(customerClientDto1,customerClientDto2));
        // When
        List<CustomerClientDto> result = customerService.getCustomers(List.of(1, 2));
        // Then
        assertEquals(2, result.size());
        assertEquals(1, result.get(0).getCustomerId());
        assertEquals(2, result.get(1).getCustomerId());
        verify(customerClient, times(1)).filterCustomers(List.of(1,2));
    }

}
