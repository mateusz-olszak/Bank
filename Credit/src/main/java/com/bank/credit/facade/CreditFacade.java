package com.bank.credit.facade;

import com.bank.credit.dto.*;
import com.bank.credit.service.CreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CreditFacade {

    private final CreditService creditService;

    @Autowired
    public CreditFacade(CreditService creditService) {
        this.creditService = creditService;
    }

    public CreditNumberDto createCredit(CreditDto creditDto) {
        return creditService.createCredit(creditDto);
    }

    public ResultCreditDto getCredits() {
        List<CreditDto> credits = creditService.getCredits();
        return new ResultCreditDto(credits);
    }
}
