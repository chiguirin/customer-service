package com.bank.customer.infrastructure.controller.dto;


public record CreateCustomerRequestDTO(
        String customerId,
        String password,
        String name,
        String gender,
        Integer age,
        String address,
        String phone
) {
}