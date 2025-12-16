package com.bank.customer.infrastructure.controller.dto;

public record CustomerResponseDTO(
        String customerId,
        Boolean active,
        String name,
        String gender,
        Integer age,
        String address,
        String phone
) {}