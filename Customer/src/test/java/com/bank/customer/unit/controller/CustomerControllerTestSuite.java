package com.bank.customer.unit.controller;

import com.bank.customer.controller.CustomerController;
import com.bank.customer.dto.*;
import com.bank.customer.facade.CustomerFacade;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebMvcTest(controllers = CustomerController.class)
public class CustomerControllerTestSuite {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerFacade customerFacade;


    @Test
    void test_createCustomer() throws Exception {
        // Given
        CustomerDto customerDto = new CustomerDto("Jan", "Kowalski", "85123456789");
        Gson gson = new Gson();
        String jsonContent = gson.toJson(customerDto);
        CustomerIdDto customerIdDto = new CustomerIdDto(1);
        when(customerFacade.createCustomer(any())).thenReturn(customerIdDto);
        // When Then
        mockMvc.perform(MockMvcRequestBuilders.post("/customer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.customerId").value(1));
    }

    @Test
    void test_searchCustomer_thereIsRecordInDb() throws Exception {
        // Given
        CustomerDto customerDto = new CustomerDto("Jan", "Kowalski", "85123456789");
        List<CustomerDto> customerDtos = List.of(customerDto);
        CustomerSearchDto customerSearchDto = new CustomerSearchDto(customerDtos);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(customerDto);
        when(customerFacade.searchCustomer(any())).thenReturn(customerSearchDto);
        // When Then
        mockMvc.perform(MockMvcRequestBuilders.post("/customer/search")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.searchResult.size()").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.searchResult[0].firstName").value("Jan"));
    }

    @Test
    void test_searchCustomer_noRecordInDb() throws Exception {
        // Given
        CustomerDto customerDto = new CustomerDto("Jan", "Kowalski", "85123456789");
        List<CustomerDto> customerDtos = List.of(customerDto);
        CustomerSearchDto customerSearchDto = new CustomerSearchDto(customerDtos);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(customerDto);
        when(customerFacade.searchCustomer(any())).thenReturn(customerSearchDto);
        // When Then
        mockMvc.perform(MockMvcRequestBuilders.post("/customer/search")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.searchResult.size()").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.searchResult[0].firstName").value("Jan"));
    }

    @Test
    void test_getCustomers() throws Exception {
        // Given
        CustomerDto customerDto1 = new CustomerDto("Jan", "Kowalski", "85123456789");
        CustomerDto customerDto2 = new CustomerDto("Josh", "Bezos", "77123456789");
        CustomerDto customerDto3 = new CustomerDto("Adam", "Joseph", "99123456789");
        List<CustomerDto> customerDtos = List.of(customerDto1,customerDto2,customerDto3);
        CustomerFilteredDto customerFilteredDto = new CustomerFilteredDto(customerDtos);
        List<Integer> customersIds = List.of(1,2,3);
        CustomerFilteredIdDto customerFilteredIdDto = new CustomerFilteredIdDto(customersIds);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(customerFilteredIdDto);
        when(customerFacade.getCustomers(any())).thenReturn(customerFilteredDto);
        // When Then
        mockMvc.perform(MockMvcRequestBuilders.post("/customer/filtered")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.customers.size()").value(3));
    }
}
