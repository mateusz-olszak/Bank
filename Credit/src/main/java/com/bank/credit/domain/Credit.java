package com.bank.credit.domain;

import javax.persistence.*;

@Entity
@Table(name = "CREDITS")
public class Credit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CREDIT_ID")
    private Integer id;
    @Column(name = "CUSTOMER_ID", nullable = false)
    private Integer customerId;
    @Column(name = "CREDIT_NAME")
    private String creditName;
    @Column(name = "VALUE", nullable = false)
    private double value;

    public Credit() {
    }

    public Credit(Integer id, Integer customerId, String creditName, double value) {
        this.id = id;
        this.customerId = customerId;
        this.creditName = creditName;
        this.value = value;
    }

    public Credit(Integer customerId, String creditName, double value) {
        this.customerId = customerId;
        this.creditName = creditName;
        this.value = value;
    }

    private void setId(Integer id) {
        this.id = id;
    }

    private void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    private void setCreditName(String creditName) {
        this.creditName = creditName;
    }

    private void setValue(double value) {
        this.value = value;
    }

    public Integer getId() {
        return id;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public String getCreditName() {
        return creditName;
    }

    public double getValue() {
        return value;
    }
}
