package com.bank.credit.dto;

import java.util.List;

public class ResultCreditDto {

    private final List<CreditDto> credits;

    public ResultCreditDto(List<CreditDto> creditDto) {
        this.credits = creditDto;
    }

    public List<CreditDto> getCredits() {
        return credits;
    }
}
