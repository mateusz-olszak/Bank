package com.bank.customer;

import com.bank.customer.dao.CustomerRepository;
import com.bank.customer.domain.Customer;
import com.bank.customer.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class CustomerTestSuite {

    @InjectMocks
    private CustomerService customerService;

    @Mock
    private CustomerRepository customerRepository;

    @Test
    void test_createCustomer() {
        // Given
        Customer customer = new Customer("Jan","Kowalski","85123456789");
        when(customerRepository.save(customer)).thenReturn(customer);
        // When
        Customer savedCustomer = customerService.createCustomer(customer);
        // Then
        assertEquals("Jan",savedCustomer.getFirstName());
        assertEquals("Kowalski",savedCustomer.getLastName());
        verify(customerRepository, times(1)).save(customer);
    }

}
