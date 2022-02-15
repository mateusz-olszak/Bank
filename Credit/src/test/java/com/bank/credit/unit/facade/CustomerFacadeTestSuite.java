package com.bank.credit.unit.facade;

import com.bank.credit.dto.CreditDto;
import com.bank.credit.dto.CreditNumberDto;
import com.bank.credit.dto.ResultCreditDto;
import com.bank.credit.facade.CreditFacade;
import com.bank.credit.service.CreditService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CustomerFacadeTestSuite {

    @InjectMocks
    private CreditFacade creditFacade;

    @Mock
    private CreditService creditService;

    @Test
    void test_createCredit() {
        // Given
        CreditDto creditDto = new CreditDto();
        CreditNumberDto creditNumberDto = new CreditNumberDto(1);
        when(creditService.createCredit(creditDto)).thenReturn(creditNumberDto);
        // When
        CreditNumberDto result = creditFacade.createCredit(creditDto);
        // Then
        assertEquals(1,result.getCreditId());
        verify(creditService, times(1)).createCredit(creditDto);
    }

    @Test
    void test_getCredits() {
        // Given
        CreditDto creditDto1 = new CreditDto();
        CreditDto creditDto2 = new CreditDto();
        when(creditService.getCredits()).thenReturn(List.of(creditDto1,creditDto2));
        // When
        ResultCreditDto result = creditFacade.getCredits();
        // Then
        assertEquals(2, result.getCredits().size());
        verify(creditService, times(1)).getCredits();
    }
}
