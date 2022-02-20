package com.bank.credit.unit.client;

import com.bank.credit.client.CustomerClient;
import com.bank.credit.config.CustomerConfig;
import com.bank.credit.dto.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CustomerClientTestSuite {

    @InjectMocks
    private CustomerClient customerClient;

    @Mock
    private RestTemplate restTemplate;
    @Mock
    private CustomerConfig customerConfig;

    @Test
    void test_createCustomer() {
        // Given
        CustomerDto customerDto = new CustomerDto("Jan","Kowalski","85123456789");
        CustomerIdDto customerIdDto = new CustomerIdDto(1);
        String url = "http://test.com/";
        when(customerConfig.getCustomerPath()).thenReturn(url);
        when(restTemplate.postForObject(any(URI.class),any(HttpEntity.class),eq(CustomerIdDto.class))).thenReturn(customerIdDto);
        // When
        Optional<CustomerIdDto> result = customerClient.createCustomer(customerDto);
        // Then
        assertEquals(1,result.get().getCustomerId());
        verify(customerConfig, times(1)).getCustomerPath();
        verify(restTemplate, times(1)).postForObject(any(URI.class), any(HttpEntity.class), eq(CustomerIdDto.class));
    }

    @Test
    void test_searchCustomer() {
        // Given
        CustomerDto customerDto = new CustomerDto("Jan","Kowalski","85123456789");
        CustomerClientDto customerClientDto1 = new CustomerClientDto(1, "Jan","Kowalski","85123456789");
        CustomerClientDto customerClientDto2 = new CustomerClientDto(2, "Adam","Joseph","86123456789");
        CustomerSearchDto customerSearchDto = new CustomerSearchDto(new CustomerClientDto[]{customerClientDto1,customerClientDto2});
        String url = "http://test.com/";
        when(customerConfig.getCustomerPath()).thenReturn(url);
        when(restTemplate.postForObject(any(URI.class), any(HttpEntity.class), eq(CustomerSearchDto.class))).thenReturn(customerSearchDto);
        // When
        List<CustomerClientDto> result = customerClient.searchCustomer(customerDto);
        // Then
        assertEquals(2, result.size());
        verify(customerConfig, times(1)).getCustomerPath();
        verify(restTemplate, times(1)).postForObject(any(URI.class), any(HttpEntity.class), eq(CustomerSearchDto.class));
    }

    @Test
    void test_filterCustomers() {
        // Given
        List<Integer> customersIds = List.of(1,2);
        CustomerClientDto customerClientDto1 = new CustomerClientDto(1, "Jan","Kowalski","85123456789");
        CustomerClientDto customerClientDto2 = new CustomerClientDto(2, "Adam","Joseph","86123456789");
        CustomerFilteredDto customerFilteredDto = new CustomerFilteredDto(new CustomerClientDto[]{customerClientDto1, customerClientDto2});
        String url = "http://test.com";
        when(customerConfig.getCustomerPath()).thenReturn(url);
        when(restTemplate.postForObject(any(URI.class), any(HttpEntity.class), eq(CustomerFilteredDto.class))).thenReturn(customerFilteredDto);
        // When
        List<CustomerClientDto> result = customerClient.filterCustomers(customersIds);
        // Then
        assertEquals(2, result.size());
        verify(customerConfig, times(1)).getCustomerPath();
        verify(restTemplate, times(1)).postForObject(any(URI.class), any(HttpEntity.class), eq(CustomerFilteredDto.class));
    }
}
