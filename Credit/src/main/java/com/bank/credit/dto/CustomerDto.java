package com.bank.credit.dto;

public class CustomerDto {

    private String firstName;
    private String lastName;
    private String pesel;

    public CustomerDto() {
    }

    public CustomerDto(String firstName, String lastName, String pesel) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.pesel = pesel;
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
