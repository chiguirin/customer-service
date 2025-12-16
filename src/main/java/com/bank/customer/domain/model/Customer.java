package com.bank.customer.domain.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customerId;
    private String password;
    private Boolean active;

    @OneToOne(optional = false)
    private Person person;

    protected Customer() {
    }

    public Customer(String customerId, String password, Person person) {
        this.customerId = customerId;
        this.password = password;
        this.person = person;
        this.active = Boolean.TRUE;
    }

    public Long getId() {
        return id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public Boolean isActive() {
        return active;
    }

    public Person getPerson() {
        return person;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}