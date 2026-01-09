package com.bank.customer.domain.model;


import jakarta.persistence.*;

@MappedSuperclass
public abstract class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Version
    protected Long version;

    protected String name;
    protected String gender;
    protected Integer age;
    protected String address;
    protected String phone;

    protected Person() {
    }

    protected Person(String name, String gender, Integer age, String address, String phone) {
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.address = address;
        this.phone = phone;
    }

    public Long getId() {
        return id;
    }

    public Long getVersion() {
        return version;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public Integer getAge() {
        return age;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }
}
