package com.bank.credit.controller;

import com.bank.credit.dto.CreditDto;
import com.bank.credit.dto.CreditNumberDto;
import com.bank.credit.dto.ResultCreditDto;
import com.bank.credit.facade.CreditFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreditController {

    private final CreditFacade creditFacade;

    @Autowired
    public CreditController(CreditFacade creditFacade) {
        this.creditFacade = creditFacade;
    }

    @PostMapping("/createCredit")
    public CreditNumberDto createCredit(@RequestBody CreditDto creditDto) {
        return creditFacade.createCredit(creditDto);
    }

    @GetMapping("/getCredits")
    public ResultCreditDto getCredits() {
        return creditFacade.getCredits();
    }

}
