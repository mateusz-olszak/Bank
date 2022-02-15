package com.bank.customer.dto;

public class CustomerDto {

    private int customerId;
    private String firstName;
    private String lastName;
    private String pesel;

    public CustomerDto() {
    }

    public CustomerDto(int customerId, String firstName, String lastName, String pesel) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.pesel = pesel;
    }

    public CustomerDto(String firstName, String lastName, String pesel) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.pesel = pesel;
    }

    public int getCustomerId() {
        return customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPesel() {
        return pesel;
    }
}
