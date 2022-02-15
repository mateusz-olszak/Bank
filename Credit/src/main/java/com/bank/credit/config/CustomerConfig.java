package com.bank.credit.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CustomerConfig {

    @Value("${customer.application.path}")
    private String customerPath;

    public String getCustomerPath() {
        return customerPath;
    }
}
