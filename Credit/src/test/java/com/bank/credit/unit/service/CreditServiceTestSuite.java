package com.bank.credit.unit.service;

import com.bank.credit.dao.CreditRepository;
import com.bank.credit.domain.Credit;
import com.bank.credit.dto.*;
import com.bank.credit.service.CreditService;
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
public class CreditServiceTestSuite {

    @InjectMocks
    private CreditService creditService;

    @Mock
    private CreditRepository creditRepository;
    @Mock
    private CustomerService customerService;

    @Test
    void test_createCustomer_customerExists() {
        // Given
        CustomerDto customerDto = new CustomerDto("Jan","Kowalski","80123456789");
        CreditDto creditDto = new CreditDto("Hipoteczny",350000,customerDto);
        CustomerClientDto customerClientDto = new CustomerClientDto(1,"Jan","Kowalski","80123456789");
        Credit credit = new Credit(1, 1, "Hipoteczny",350000);
        when(customerService.searchCustomer(any())).thenReturn(customerClientDto);
        when(creditRepository.save(any())).thenReturn(credit);
        // When
        CreditNumberDto resultCredit = creditService.createCredit(creditDto);
        // Then
        assertEquals(1, resultCredit.getCreditId());
        verify(customerService, times(1)).searchCustomer(any());
        verify(creditRepository, times(1)).save(any());
    }

    @Test
    void test_createCustomer_customerNotExists() {
        // Given
        CustomerDto customerDto = new CustomerDto("Jan","Kowalski","80123456789");
        CreditDto creditDto = new CreditDto("Hipoteczny",350000,customerDto);
        Credit credit = new Credit(1, 1, "Hipoteczny",350000);
        CustomerIdDto customerIdDto = new CustomerIdDto(1);
        when(customerService.searchCustomer(any())).thenReturn(null);
        when(customerService.createCustomer(customerDto)).thenReturn(customerIdDto);
        when(creditRepository.save(any())).thenReturn(credit);
        // When
        CreditNumberDto resultCredit = creditService.createCredit(creditDto);
        // Then
        assertEquals(1, resultCredit.getCreditId());
        verify(customerService, times(1)).searchCustomer(any());
        verify(customerService, times(1)).createCustomer(customerDto);
        verify(creditRepository, times(1)).save(any());
    }

    @Test
    void test_getCredits_oneCustomerHasTwoCredits() {
        // Given
        Credit credit1 = new Credit(1,1,"Inwestycyjny",350000);
        Credit credit2 = new Credit(2,1,"Gotówkowy",15000);
        List<Credit> credits = List.of(credit1,credit2);
        CustomerClientDto customerClientDto = new CustomerClientDto(1, "Jan", "Kowalski", "85123456789");
        List<CustomerClientDto> customerClientDtos = List.of(customerClientDto);
        when(creditRepository.findAll()).thenReturn(credits);
        when(customerService.getCustomers(any())).thenReturn(customerClientDtos);
        // When
        List<CreditDto> resultList = creditService.getCredits();
        // Then
        assertEquals(2, resultList.size());
        verify(creditRepository, times(1)).findAll();
        verify(customerService, times(1)).getCustomers(any());
    }

    @Test
    void test_getCredits_customerHasJustOneCredit() {
        // Given
        Credit credit1 = new Credit(1,1,"Inwestycyjny",350000);
        Credit credit2 = new Credit(2,2,"Gotówkowy",15000);
        List<Credit> credits = List.of(credit1,credit2);
        CustomerClientDto customerClientDto1 = new CustomerClientDto(1, "Jan", "Kowalski", "85123456789");
        CustomerClientDto customerClientDto2 = new CustomerClientDto(2, "Michał", "Jakubiak", "90123456789");
        List<CustomerClientDto> customerClientDtos = List.of(customerClientDto1,customerClientDto2);
        when(creditRepository.findAll()).thenReturn(credits);
        when(customerService.getCustomers(any())).thenReturn(customerClientDtos);
        // When
        List<CreditDto> resultList = creditService.getCredits();
        // Then
        assertEquals(2, resultList.size());
        assertEquals("Jan", resultList.get(0).getCustomer().getFirstName());
        assertEquals("Michał", resultList.get(1).getCustomer().getFirstName());
        verify(creditRepository, times(1)).findAll();
        verify(customerService, times(1)).getCustomers(any());
    }
}
