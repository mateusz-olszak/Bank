package com.bank.credit.dto;

public class CreditDto {

    private int creditId;
    private String creditName;
    private double value;
    private CustomerDto customer;

    public CreditDto() {
    }

    public CreditDto(int creditId, String creditName, double value, CustomerDto customer) {
        this.creditId = creditId;
        this.creditName = creditName;
        this.value = value;
        this.customer = customer;
    }

    public CreditDto(String creditName, double value, CustomerDto customer) {
        this.creditName = creditName;
        this.value = value;
        this.customer = customer;
    }

    public int getCreditId() {
        return creditId;
    }

    public String getCreditName() {
        return creditName;
    }

    public double getValue() {
        return value;
    }

    public CustomerDto getCustomer() {
        return customer;
    }
}
