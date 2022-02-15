package com.bank.customer.integration;

import com.bank.customer.dao.CustomerRepository;
import com.bank.customer.domain.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class CustomerTestSuite {

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    void test_createCustomer() {
        // Given
        Customer customer = new Customer("Jan", "Kowalski", "78643262132");
        // When
        Customer savedCustomer = customerRepository.save(customer);
        // Then
        int id = savedCustomer.getId();
        System.out.println("createCustomer id: " + id);
        assertEquals(1,id);
        // Clean up
        customerRepository.deleteById(id);
    }

    @Test
    void test_searchCustomer_findCustomerByPesel_peselMatchingRecord() {
        // Given
        String pesel = "02092678911";
        Customer customer = new Customer("Jan", "Kowalski", pesel);
        Customer savedCustomer = customerRepository.save(customer);
        int id = savedCustomer.getId();
        System.out.println("searchCustomer_match id: " + id);
        // When
        List<Customer> resultList = customerRepository.findAllByPesel(pesel);
        // Then
        assertEquals(1, resultList.size());
        assertEquals(1, resultList.get(0).getId());
        // Clean up
        customerRepository.deleteById(id);
    }

    @Test
    void test_searchCustomer_findCustomerByPesel_peselNotMatchingRecord() {
        // Given
        String pesel = "wrong_pesel";
        Customer customer = new Customer("Jan", "Kowalski", "88010178911");
        Customer savedCustomer = customerRepository.save(customer);
        int id = savedCustomer.getId();
        System.out.println("searchCustomer_nomatch id: " + id);
        // When
        List<Customer> resultCustomers = customerRepository.findAllByPesel(pesel);
        // Then
        assertEquals(0, resultCustomers.size());
        // Clean up
        customerRepository.deleteById(id);
    }

    @Test
    void test_getCustomers_findAllCustomers_containingIdsGivenInList_idsMatching() {
        // Given
        List<Integer> customersIds = List.of(1,2,3);
        Customer customer1 = new Customer("Jan","Kowalski","88345678911");
        Customer customer2 = new Customer("Adam","Joseph","76123512672");
        Customer customer3 = new Customer("Stephen","Curry","91236176213");
        Customer savedCustomer1 = customerRepository.save(customer1);
        Customer savedCustomer2 = customerRepository.save(customer2);
        Customer savedCustomer3 = customerRepository.save(customer3);
        int idCustomer1 = savedCustomer1.getId();
        int idCustomer2 = savedCustomer2.getId();
        int idCustomer3 = savedCustomer3.getId();
        System.out.println("getCustomers_contain 1: " + idCustomer1 + " 2: " + idCustomer2 + " 3: " + idCustomer3);
        // When
        List<Customer> resultCustomers = customerRepository.findAllByIdIn(customersIds);
        // Then
        assertEquals(3, resultCustomers.size());
        assertEquals(1, resultCustomers.get(0).getId());
        assertEquals(2, resultCustomers.get(1).getId());
        assertEquals(3, resultCustomers.get(2).getId());
        // Clean up
        customerRepository.deleteById(idCustomer1);
        customerRepository.deleteById(idCustomer2);
        customerRepository.deleteById(idCustomer3);
    }

    @Test
    void test_getCustomers_findAllCustomers_containingIdsGivenInList_idsNotMatching() {
        // Given
        List<Integer> customersIds = List.of(5,6,7);
        Customer customer1 = new Customer("Jan","Kowalski","88436778911");
        Customer customer2 = new Customer("Adam","Joseph","76102282672");
        Customer customer3 = new Customer("Stephen","Curry","91231176213");
        Customer savedCustomer1 = customerRepository.save(customer1);
        Customer savedCustomer2 = customerRepository.save(customer2);
        Customer savedCustomer3 = customerRepository.save(customer3);
        int idCustomer1 = savedCustomer1.getId();
        int idCustomer2 = savedCustomer2.getId();
        int idCustomer3 = savedCustomer3.getId();
        System.out.println("getCustomers_contain 1: " + idCustomer1 + " 2: " + idCustomer2 + " 3: " + idCustomer3);System.out.println("getCustomers_dontcontain 1: " + idCustomer1 + " 2: " + idCustomer2 + " 3: " + idCustomer3);
        // When
        List<Customer> resultCustomers = customerRepository.findAllByIdIn(customersIds);
        // Then
        assertEquals(0, resultCustomers.size());
        // Clean up
        customerRepository.deleteById(idCustomer1);
        customerRepository.deleteById(idCustomer2);
        customerRepository.deleteById(idCustomer3);
    }
}

