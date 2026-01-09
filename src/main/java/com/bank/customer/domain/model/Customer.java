package com.bank.customer.domain.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "customers")
public class Customer extends Person {

    @Column(unique = true, nullable = false)
    private String customerId;

    private String password;
    private Boolean active;

    protected Customer() {
    }

    public Customer(String customerId,
                    String password,
                    String name,
                    String gender,
                    Integer age,
                    String address,
                    String phone) {

        super(name, gender, age, address, phone);
        this.customerId = customerId;
        this.password = password;
        this.active = Boolean.TRUE;
    }

    public String getCustomerId() {
        return customerId;
    }

    public Boolean isActive() {
        return active;
    }

    public String getPassword() {
        return password;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}