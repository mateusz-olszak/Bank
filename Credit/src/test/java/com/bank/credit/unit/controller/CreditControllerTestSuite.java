package com.bank.credit.unit.controller;

import com.bank.credit.controller.CreditController;
import com.bank.credit.dto.CreditDto;
import com.bank.credit.dto.CreditNumberDto;
import com.bank.credit.dto.CustomerDto;
import com.bank.credit.dto.ResultCreditDto;
import com.bank.credit.facade.CreditFacade;
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

@WebMvcTest(controllers = CreditController.class)
public class CreditControllerTestSuite {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CreditFacade creditFacade;

    @Test
    void test_createCredit() throws Exception {
        // Given
        CreditNumberDto creditNumberDto = new CreditNumberDto(1);
        CreditDto creditDto = new CreditDto();
        Gson gson = new Gson();
        String jsonPath = gson.toJson(creditDto);
        when(creditFacade.createCredit(any())).thenReturn(creditNumberDto);
        // When Then
        mockMvc.perform(MockMvcRequestBuilders.post("/createCredit")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonPath))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.creditId").value(1));
    }

    @Test
    void test_getCredits() throws Exception {
        // Given
        CreditDto creditDto1 = new CreditDto("Hipoteczny", 500000, new CustomerDto("Jan","Kowalski","851123456789"));
        CreditDto creditDto2 = new CreditDto("Inwestycyjny", 500000, new CustomerDto("Adam","Nowak","901123456789"));
        ResultCreditDto resultCreditDto = new ResultCreditDto(List.of(creditDto1, creditDto2));
        when(creditFacade.getCredits()).thenReturn(resultCreditDto);
        // When Then
        mockMvc.perform(MockMvcRequestBuilders.get("/getCredits")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.credits.size()").value(2));
    }
}
